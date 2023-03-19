package io.searching.server.api.searchranking.adapter.inp.web

import io.searching.server.core.searchranking.application.port.inp.GetSearchRanking
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/search-ranking")
class SearchRankingApi(
    private val getSearchRanking: GetSearchRanking
) {

    @GetMapping
    fun getSearchRanking(): ResponseEntity<SearchRankingRes> {
        val searchRankings = getSearchRanking.getTopTen()

        return ResponseEntity.ok(SearchRankingRes.of(searchRankings))
    }
}
