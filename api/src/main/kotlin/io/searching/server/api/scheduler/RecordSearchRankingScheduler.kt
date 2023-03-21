package io.searching.server.api.scheduler

import io.searching.server.api.searchranking.adapter.inp.service.SearchRankingAppService
import mu.KotlinLogging
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger { }

@Component
class RecordSearchRankingScheduler(
    private val searchRankingAppService: SearchRankingAppService
) {

    @Scheduled(fixedDelay = THIRTY_SECONDS)
    fun recordSearchRanking() {
        logger.info { "RecordSearchRankingScheduler Start" }

        searchRankingAppService.recordSearchRanking()
    }

    companion object {
        private const val THIRTY_SECONDS = 30000L
    }
}
