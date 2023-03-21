package io.searching.server.api.blog.service

import io.searching.server.integration.blog.BlogSearcher
import io.searching.server.integration.blog.BlogSearcherDto
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface BlogAppService {

    /**
     * 블로그 검색
     * @event BlogSearchedEvent
     */
    fun search(spec: BlogSearchSpec): BlogSearcherDto
}

@Service
@Transactional
class DefaultBlogAppService(
    private val blogSearcher: BlogSearcher,
    private val eventPublisher: ApplicationEventPublisher
) : BlogAppService {
    override fun search(spec: BlogSearchSpec): BlogSearcherDto {
        return blogSearcher.search(spec.keyword, spec.sortType, spec.page).also {
            eventPublisher.publishEvent(BlogSearchedEvent(spec.keyword))
        }
    }
}
