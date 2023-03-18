package io.searching.server.api.blog.adapter.inp.web

import io.searching.server.integration.blog.SortType
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

class BlogSearchReq(
    @field:NotBlank
    val keyword: String,

    val sort: SortType? = null,

    @field:Min(1) @field:Max(50)
    val page: Int = 1
)
