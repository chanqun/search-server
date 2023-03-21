package io.searching.server.api.searchranking.adapter.inp.service

import io.searching.server.api.searchranking.adapter.inp.event.KeywordCounter
import org.springframework.stereotype.Service

interface SearchRankingAppService {

    /**
     * 검색어 순위 랭킹 조회
     */
    fun getTopTen(): List<Pair<String, Int>>
}

@Service
class DefaultSearchRankingAppService(
    private val keywordCounter: KeywordCounter
) : SearchRankingAppService {
    override fun getTopTen(): List<Pair<String, Int>> {
        return keywordCounter.getTopTen()
    }
}
