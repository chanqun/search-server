package io.searching.server.integration.blog.naver

import io.searching.server.integration.blog.Document
import java.time.OffsetDateTime

class NaverBlogSearchRes(
    val start: Int,
    val display: Int,
    val total: Int,
    val items: List<NaverBlogDocument> = emptyList()
) {
    val isEnd: Boolean
        get() = start > total
}

class NaverBlogDocument(
    val title: String,
    val description: String,
    val link: String,
    val bloggername: String,
    val postdate: OffsetDateTime
) {
    fun toDocument(): Document {
        return Document(
            title = title, contents = description, url = link,
            blogName = bloggername, thumbnail = "noThumbNail", datetime = postdate,
        )
    }
}
