package io.searching.server.core.searchranking.application.port.inp

import io.searching.server.core.searchranking.domain.SearchRanking

interface RecordSearchRanking {

    /**
     * 인기 검색어 랭킹 조회 도는 생성 후
     * 검색 횟수를 증가한다.
     */
    fun record(command: RecordSearchRankingCommand): SearchRanking
}

data class RecordSearchRankingCommand(
    val keyword: String
)
