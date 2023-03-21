package io.searching.server.api.searchranking.adapter.inp.web

import io.searching.server.core.searchranking.application.port.inp.GetTopTenSearchRanking
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger { }

@RestController
@RequestMapping("/search-ranking")
class SearchRankingApi(
    private val getTopTenSearchRanking: GetTopTenSearchRanking
) {

    @GetMapping
    fun getSearchRanking(): SearchRankingRes {
        logger.info { "[GET] /search-ranking" }

        val searchRankings = getTopTenSearchRanking.get()

        return SearchRankingRes.of(searchRankings)
    }
}
