package io.searching.server.api.searchranking.adapter.inp.web

import io.searching.server.core.searchranking.domain.SearchRanking

class SearchRankingRes(
    val searchRankings: List<SearchRankingData>
) {
    companion object {
        fun of(searchRankings: List<SearchRanking>): SearchRankingRes {
            return SearchRankingRes(searchRankings.mapIndexed { index, searchRanking ->
                SearchRankingData(ranking = index + 1, keyword = searchRanking.keyword, count = searchRanking.count)
            })
        }
    }
}

class SearchRankingData(
    val ranking: Int,
    val keyword: String,
    val count: Int
)
