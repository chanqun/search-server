package io.searching.server.api.blog.adapter.inp.web

import io.searching.server.integration.blog.BlogSearcherDto
import io.searching.server.integration.blog.SortType
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Range
import java.time.OffsetDateTime

data class BlogSearchReq(
    @field:NotBlank
    val keyword: String,

    val sort: SortType = SortType.ACCURACY,

    @field:Range(min = 1, max = 50)
    val page: Int = 1
)

class BlogSearchRes(
    val page: Int,
    val isEnd: Boolean,
    val documents: List<BlogDocumentData>
) {
    companion object {
        fun of(blogSearcherDto: BlogSearcherDto): BlogSearchRes {
            return with(blogSearcherDto) {
                BlogSearchRes(page, isEnd, documents.map {
                    BlogDocumentData(
                        title = it.title, contents = it.contents, url = it.url,
                        blogName = it.blogName, thumbnail = it.thumbnail, datetime = it.datetime
                    )
                })
            }
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
