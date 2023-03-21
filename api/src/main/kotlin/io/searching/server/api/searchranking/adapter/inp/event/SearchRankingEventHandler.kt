package io.searching.server.api.searchranking.adapter.inp.event

import io.searching.server.api.blog.service.BlogSearchedEvent
import io.searching.server.core.searchranking.application.port.inp.RecordSearchRanking
import io.searching.server.core.searchranking.application.port.inp.RecordSearchRankingCommand
import mu.KotlinLogging
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionalEventListener

private val logger = KotlinLogging.logger { }

@Component
class SearchRankingEventHandler(
    private val recordSearchRanking: RecordSearchRanking,
) {

    @Async
    @TransactionalEventListener(BlogSearchedEvent::class)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun onBlogSearchedEvent(event: BlogSearchedEvent) {
        repeat(10) {
            try {
                recordSearchRanking.record(RecordSearchRankingCommand(event.keyword))
                return
            } catch (e: Exception) {
                logger.error(e) { "검색 기록 실패" }

                Thread.sleep(50)
            }
        }
    }
}
