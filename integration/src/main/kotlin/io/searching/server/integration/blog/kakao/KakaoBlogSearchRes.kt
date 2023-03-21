package io.searching.server.integration.blog.kakao

import com.fasterxml.jackson.annotation.JsonProperty
import io.searching.server.integration.blog.Document
import java.time.OffsetDateTime

class KakaoBlogSearchRes(
    val meta: KakaoBlogMeta,
    val documents: List<KakaoBlogDocument> = emptyList()
)

class KakaoBlogMeta(
    @JsonProperty("total_count")
    val totalCount: Int,
    @JsonProperty("pageable_count")
    val pageableCount: Int,
    @JsonProperty("isEnd")
    val isEnd: Boolean,
)

class KakaoBlogDocument(
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
