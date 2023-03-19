package io.searching.server.core.searchranking.application.port.inp

import io.searching.server.core.searchranking.domain.SearchRanking

interface FindOrCreateSearchRanking {

    /**
     * 인기 검색어 랭킹 생성
     * 검색 랭킹이 있으면 반환한다.
     */
    fun findOrCreate(command: FindOrCreateSearchRankingCommand): SearchRanking
}

data class FindOrCreateSearchRankingCommand(
    val keyword: String
)
