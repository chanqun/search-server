package io.searching.server.integration.blog.kakao

import io.searching.server.integration.blog.Documents
import org.springframework.data.domain.Page
import java.time.OffsetDateTime

data class KakaoBlogSearchRes(
    val meta: KakaoBlogMeta,
    val documents: List<KakaoBlogDocument> = emptyList()
) {
    fun toPage(): Page<Documents> {
        return Page.empty()
    }
}

data class KakaoBlogMeta(
    val totalCount: Int,
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
)
