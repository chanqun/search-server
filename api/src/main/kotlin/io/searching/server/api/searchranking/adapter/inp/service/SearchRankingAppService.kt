package io.searching.server.api.searchranking.adapter.inp.service

import io.searching.server.api.searchranking.adapter.inp.event.KeywordCounter
import io.searching.server.core.searchranking.application.port.inp.RecordSearchRanking
import io.searching.server.core.searchranking.application.port.inp.RecordSearchRankingCommand
import org.springframework.stereotype.Service

interface SearchRankingAppService {

    /**
     * 검색어 순위 랭킹 조회
     */
    fun getTopTen(): List<Pair<String, Int>>

    /**
     * 검색어 순위 저장
     */
    fun recordSearchRanking()
}

@Service
class DefaultSearchRankingAppService(
    private val keywordCounter: KeywordCounter,
    private val recordSearchRanking: RecordSearchRanking
) : SearchRankingAppService {
    override fun getTopTen(): List<Pair<String, Int>> {
        return keywordCounter.getTopTen()
    }

    override fun recordSearchRanking() {
        keywordCounter.getAll().forEach { (keyword, count) ->
            recordSearchRanking.record(RecordSearchRankingCommand(keyword, count))
        }
    }
}
