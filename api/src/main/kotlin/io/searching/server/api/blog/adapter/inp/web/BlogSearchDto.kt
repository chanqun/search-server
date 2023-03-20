package io.searching.server.api.blog.adapter.inp.web

import io.searching.server.integration.blog.Document
import io.searching.server.integration.blog.SortType
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import java.time.OffsetDateTime

class BlogSearchReq(
    @field:NotBlank
    val keyword: String,

    val sort: SortType? = null,

    @field:Min(1) @field:Max(50)
    val page: Int = 1
)

class BlogSearchRes(
    val page: Int,
    val isEnd: Boolean,
    val documents: List<BlogDocumentData>
) {
    companion object {
        fun of(page: Int, isEnd: Boolean, documents: List<Document>): BlogSearchRes {
            return BlogSearchRes(page, isEnd, documents.map {
                BlogDocumentData(
                    title = it.title, contents = it.contents, url = it.url,
                    blogName = it.blogName, thumbnail = it.thumbnail, datetime = it.datetime
                )
            })
        }
    }
}

class BlogDocumentData(
    val title: String,
    val contents: String,
    val url: String,
    val blogName: String,
    val thumbnail: String,
    val datetime: OffsetDateTime
)
