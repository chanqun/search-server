package io.searching.server.api.blog.service

import io.searching.server.integration.blog.SortType

class BlogSearchSpec(
    val keyword: String,
    val sortType: SortType,
    val page: Int
)
