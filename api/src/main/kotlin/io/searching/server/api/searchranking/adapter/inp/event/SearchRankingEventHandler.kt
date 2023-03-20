package io.searching.server.api.searchranking.adapter.inp.event

import io.searching.server.api.blog.service.BlogSearchedEvent
import io.searching.server.core.searchranking.application.port.inp.RecordSearchRanking
import io.searching.server.core.searchranking.application.port.inp.RecordSearchRankingCommand
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.transaction.event.TransactionalEventListener

@Service
class SearchRankingEventHandler(
    private val recordSearchRanking: RecordSearchRanking
) {

    @Async
    @TransactionalEventListener(BlogSearchedEvent::class)
    fun onBlogSearchedEvent(event: BlogSearchedEvent) {
        recordSearchRanking.record(RecordSearchRankingCommand(event.keyword))
    }
}
