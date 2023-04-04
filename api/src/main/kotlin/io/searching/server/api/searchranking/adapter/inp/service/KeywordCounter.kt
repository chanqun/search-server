package io.searching.server.api.searchranking.adapter.inp.service

import io.searching.server.core.searchranking.application.port.outp.SearchRankingRepository
import org.springframework.data.redis.core.RedisTemplate
import javax.annotation.PostConstruct
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

interface KeywordCounter {
    fun initialize()

    fun increase(keyword: String, count: Int = 1): Int

    fun get(keyword: String): Int

    fun getTopTen(): List<Pair<String, Int>>

    fun getAll(): List<Pair<String, Int>>
}

@Component
class InMemoryKeywordCounter(
    private val searchRankingRepository: SearchRankingRepository
) : KeywordCounter {
    @PostConstruct
    override fun initialize() {
        counter.clear()

        searchRankingRepository.findAll().forEach {
            this.increase(it.keyword, it.count)
        }
    }

    override fun increase(keyword: String, count: Int): Int {
        val atomicInteger = AtomicInteger(0)
        val result = counter.putIfAbsent(keyword, atomicInteger) ?: atomicInteger

        return result.addAndGet(count)
    }

    override fun get(keyword: String): Int {
        return counter[keyword]?.get() ?: 0
    }

    override fun getTopTen(): List<Pair<String, Int>> {
        val size = MAX_RANKING_SIZE.coerceAtMost(counter.size)

        return counter.entries.sortedByDescending { it.value.get() }
            .subList(0, size).map { Pair(it.key, it.value.get()) }
    }

    override fun getAll(): List<Pair<String, Int>> {
        return counter.entries.map { Pair(it.key, it.value.get()) }
    }

    companion object {
        private val counter: ConcurrentHashMap<String, AtomicInteger> = ConcurrentHashMap()
        private const val MAX_RANKING_SIZE = 10
    }
}

// @Component
class RedisKeywordCounter(
    private val searchRankingRepository: SearchRankingRepository,
    private val redisTemplate: RedisTemplate<String, Any>
) : KeywordCounter {
    @PostConstruct
    override fun initialize() {
        searchRankingRepository.findAll().forEach {
            this.increase(it.keyword, it.count)
        }
    }

    override fun increase(keyword: String, count: Int): Int {
        redisTemplate.opsForValue().set("${KEY}keyword", count)

        return Integer.parseInt(redisTemplate.opsForValue().get("${KEY}keyword").toString())
    }

    override fun get(keyword: String): Int {
        return Integer.parseInt(redisTemplate.opsForValue().get("${KEY}keyword").toString())
    }

    override fun getTopTen(): List<Pair<String, Int>> {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<Pair<String, Int>> {
        TODO("Not yet implemented")
    }

    companion object {
        private const val KEY = "keyword:"
    }
}
