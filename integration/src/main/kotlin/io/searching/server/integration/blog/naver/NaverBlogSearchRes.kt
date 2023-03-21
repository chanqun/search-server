package io.searching.server.integration.blog.naver

import com.fasterxml.jackson.annotation.JsonFormat
import io.searching.server.integration.blog.Document
import java.time.LocalDate
import java.time.OffsetTime

class NaverBlogSearchRes(
    val start: Int,
    val display: Int,
    val total: Int,
    val items: List<NaverBlogDocument> = emptyList()
)

class NaverBlogDocument(
    val title: String,
    val description: String,
    val link: String,
    val bloggername: String,

    @JsonFormat(pattern = "yyyyMMdd")
    val postdate: LocalDate
) {
    fun toDocument(): Document {
        return Document(
            title = title, contents = description, url = link,
            blogName = bloggername, thumbnail = "", datetime = postdate.atTime(OffsetTime.MIN),
        )
    }
}
