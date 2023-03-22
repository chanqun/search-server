package io.searching.server.api.blog.service

import io.searching.server.core.support.exception.CustomSearchingException
import io.searching.server.core.support.exception.SearchingError
import io.searching.server.integration.blog.SortType

class BlogSearchSpec(
    val keyword: String,
    val sortType: SortType,
    val page: Int
) {
    init {
        if (keyword.isBlank()) {
            throw CustomSearchingException(SearchingError.CHECK_YOUR_KEYWORD)
        }
    }
}
