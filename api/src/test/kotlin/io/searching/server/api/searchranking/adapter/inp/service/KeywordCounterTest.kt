package io.searching.server.api.searchranking.adapter.inp.service

import io.searching.server.core.searchranking.application.port.outp.SearchRankingRepository
import io.searching.server.core.searchranking.domain.SearchRanking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.CountDownLatch

@SpringBootTest
@Transactional
internal class KeywordCounterTest @Autowired constructor(
    private val keywordCounter: KeywordCounter,
    private val searchRankingRepository: SearchRankingRepository
) {
    @Test
    fun `초기화시 키워드 저장 되어 있으면 인메모리에 저장`() {
        (1..2).forEach {
            searchRankingRepository.save(SearchRanking("keyword$it", it))
        }

        keywordCounter.initialize()

        assertThat(keywordCounter.get("keyword1")).isEqualTo(1)
        assertThat(keywordCounter.get("keyword2")).isEqualTo(2)
    }

    @Test
    fun `키워드 카운트 증가 동시성 확인`() {
        keywordCounter.initialize()

        val latch = CountDownLatch(100)

        (1..100).forEach { _ ->
            Thread {
                latch.countDown()

                keywordCounter.increase("keyword")
            }.start()
        }
        latch.await()

        assertThat(keywordCounter.get("keyword")).isEqualTo(100)
    }
}
