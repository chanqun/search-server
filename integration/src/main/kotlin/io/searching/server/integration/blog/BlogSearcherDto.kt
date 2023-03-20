package io.searching.server.integration.blog

import java.time.OffsetDateTime

class BlogSearcherDto(
    val page: Int,
    val isEnd: Boolean,
    val documents: List<Document>
)

class Document(
    val title: String,
    val contents: String,
    val url: String,
    val blogName: String,
    val thumbnail: String,
    val datetime: OffsetDateTime
)
