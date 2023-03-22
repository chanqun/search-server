package io.searching.server.api.searchranking.adapter.inp.web

import io.searching.server.api.searchranking.adapter.inp.service.SearchRankingAppService
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger { }

@RestController
@RequestMapping("/search-rankings")
class SearchRankingApi(
    private val searchRankingAppService: SearchRankingAppService
) {

    @GetMapping
    fun getSearchRanking(): SearchRankingRes {
        logger.info { "[GET] /search-rankings" }

        val searchRankings = searchRankingAppService.getTopTen()

        return SearchRankingRes.of(searchRankings)
    }
}
