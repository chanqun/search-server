package io.searching.server.api.searchranking.adapter.inp.web

import io.searching.server.core.searchranking.domain.SearchRanking

class SearchRankingRes(
    val searchRankings: List<SearchRankingData>
) {
    companion object {
        fun of(searchRankings: List<SearchRanking>): SearchRankingRes {
            return SearchRankingRes(searchRankings.map { SearchRankingData(it.keyword, it.count) })
        }
    }
}

class SearchRankingData(
    val keyword: String,
    val count: Int
)
