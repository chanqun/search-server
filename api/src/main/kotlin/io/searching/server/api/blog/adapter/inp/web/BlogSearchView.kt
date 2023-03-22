package io.searching.server.api.blog.adapter.inp.web

import io.searching.server.api.blog.service.BlogSearchSpec
import io.searching.server.integration.blog.BlogSearcherDto
import io.searching.server.integration.blog.SortType
import javax.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Range
import java.time.OffsetDateTime

data class BlogSearchReq(
    @field:NotBlank
    val keyword: String = "",

    val sort: SortType = SortType.ACCURACY,

    @field:Range(min = 1, max = 50)
    val page: Int = 1
) {
    fun toSpec(): BlogSearchSpec {
        return BlogSearchSpec(convertKeywordToSpec(keyword), sort, page)
    }

    private fun convertKeywordToSpec(keyword: String): String {
        return keyword.filter { it.isLetterOrDigit() || it.isWhitespace() }.trim()
    }
}

class BlogSearchRes(
    val pageInfo: BlogSearchPageInfo,
    val documents: List<BlogDocumentData>
) {
    companion object {
        fun of(blogSearcherDto: BlogSearcherDto): BlogSearchRes {
            return with(blogSearcherDto) {
                BlogSearchRes(
                    BlogSearchPageInfo(
                        page = page, sort = sort,
                        displayCount = displayCount, totalCount = totalCount, isEnd
                    ),
                    documents.map {
                        BlogDocumentData(
                            title = it.title, contents = it.contents, url = it.url,
                            blogName = it.blogName, thumbnail = it.thumbnail, postedAt = it.datetime
                        )
                    })
            }
        }
    }
}

class BlogSearchPageInfo(
    val page: Int,
    val sort: SortType,
    val displayCount: Int,
    val totalCount: Int,
    val isEnd: Boolean,
)

class BlogDocumentData(
    val title: String,
    val contents: String,
    val url: String,
    val blogName: String,
    val thumbnail: String,
    val postedAt: OffsetDateTime
)
