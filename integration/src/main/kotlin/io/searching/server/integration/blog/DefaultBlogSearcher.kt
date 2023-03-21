package io.searching.server.integration.blog

import io.searching.server.core.support.exception.CustomSearchingException
import io.searching.server.core.support.exception.SearchingError.*
import org.springframework.stereotype.Component

@Component
class DefaultBlogSearcher(
    private val vendors: List<BlogSearchVendor>
) : BlogSearcher {
    override fun search(keyword: String, sortType: SortType, page: Int): BlogSearcherDto {
        return vendors.firstNotNullOfOrNull {
            it.search(keyword, sortType, page)
        } ?: throw CustomSearchingException(SEARCHING_SERVICE_ERROR)
    }

    companion object {
        const val PAGE_DISPLAY_CONTENTS_COUNT = 10
    }
}
