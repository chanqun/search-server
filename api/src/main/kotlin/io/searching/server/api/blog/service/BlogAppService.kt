package io.searching.server.api.blog.service

import io.searching.server.integration.blog.BlogSearcher
import io.searching.server.integration.blog.Document
import io.searching.server.integration.blog.SortType
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

interface BlogAppService {

    /**
     * 검색 후
     * 검색 완료 이벤트를 발생
     */
    fun search(keyword: String, sortType: SortType, page: Int): Triple<Int, Boolean, List<Document>>
}

@Service
class DefaultBlogAppService(
    private val blogSearcher: BlogSearcher,
    private val eventPublisher: ApplicationEventPublisher
) : BlogAppService {
    override fun search(keyword: String, sortType: SortType, page: Int): Triple<Int, Boolean, List<Document>> {
        return blogSearcher.search(keyword, sortType, page).also {
            eventPublisher.publishEvent(BlogSearchedEvent(keyword))
        }
    }
}
