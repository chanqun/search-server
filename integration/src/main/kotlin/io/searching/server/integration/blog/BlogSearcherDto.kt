package io.searching.server.integration.blog

import java.time.OffsetDateTime

class BlogSearcherDto(
    val page: Int, // 페이지
    val sort: SortType, // 정렬
    val displayCount: Int, // 한 번에 표시 되는 검색 결과 개수
    val totalCount: Int, // 총 검색 결과 개수
    val documents: List<Document>
) {
    val isEnd = (page * displayCount) >= totalCount
}

class Document(
    val title: String,
    val contents: String,
    val url: String,
    val blogName: String,
    val thumbnail: String,
    val datetime: OffsetDateTime
)
