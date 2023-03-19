package io.searching.server.api.searchranking.adapter.inp.event

import io.searching.server.api.blog.adapter.inp.web.BlogSearchedEvent
import io.searching.server.core.searchranking.application.port.inp.RecordSearchRanking
import io.searching.server.core.searchranking.application.port.inp.RecordSearchRankingCommand
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class SearchRankingEventHandler(
    private val recordSearchRanking: RecordSearchRanking
) {

    @EventListener(BlogSearchedEvent::class)
    fun onBlogSearchedEvent(event: BlogSearchedEvent) {
        recordSearchRanking.record(RecordSearchRankingCommand(event.keyword))
    }
}
