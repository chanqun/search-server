package io.searching.server.api.searchranking.adapter.inp.event

import io.searching.server.api.blog.service.BlogSearchedEvent
import io.searching.server.api.searchranking.adapter.inp.service.KeywordCounter
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionalEventListener

@Component
class SearchRankingEventHandler(
    private val keywordCounter: KeywordCounter
) {

    @Async
    @TransactionalEventListener(BlogSearchedEvent::class)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun onBlogSearchedEvent(event: BlogSearchedEvent) {
        keywordCounter.increase(event.keyword)
    }
}
