package io.searching.server.api.searchranking.adapter.inp.web

class SearchRankingRes(
    val searchRankings: List<SearchRankingData>
) {
    companion object {
        fun of(searchRankings: List<Pair<String, Int>>): SearchRankingRes {
            return SearchRankingRes(searchRankings.mapIndexed { index, searchRanking ->
                SearchRankingData(ranking = index + 1, keyword = searchRanking.first, count = searchRanking.second)
            })
        }
    }
}

class SearchRankingData(
    val ranking: Int,
    val keyword: String,
    val count: Int
)
