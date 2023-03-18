package io.searching.server.integration.blog.kakao

import io.searching.server.integration.blog.Document
import java.time.OffsetDateTime

data class KakaoBlogSearchRes(
    val meta: KakaoBlogMeta,
    val documents: List<KakaoBlogDocument> = emptyList()
)

data class KakaoBlogMeta(
    val total_count: Int,
    val pageable_count: Int,
    val is_end: Boolean,
)

data class KakaoBlogDocument(
    val title: String,
    val contents: String,
    val url: String,
    val blogname: String,
    val thumbnail: String,
    val datetime: OffsetDateTime
) {
    fun toDocument(): Document {
        return Document(
            title = title, contents = contents, url = url,
            blogName = blogname, thumbnail = thumbnail, datetime = datetime,
        )
    }
}
