package io.searching.server.integration.blog.kakao

import io.searching.server.integration.blog.BlogProperties
import io.searching.server.integration.blog.BlogSearcher
import io.searching.server.integration.blog.Document
import io.searching.server.integration.blog.SortType
import org.springframework.stereotype.Service

@Service
class KakaoBlogSearcher(
    private val kakaoBlogClient: KakaoBlogClient,
    private val blogProperties: BlogProperties
) : BlogSearcher {
    override fun search(keyword: String, sort: SortType?, page: Int): Triple<Int, Boolean, List<Document>> {
        val kakaoBlogSearchRes =
            kakaoBlogClient.search(keyword, sort, page, authorization = "KakaoAK ${blogProperties.kakaoRestApiKey}")

        return Triple(page, kakaoBlogSearchRes.meta.is_end, kakaoBlogSearchRes.documents.map { it.toDocument() })
    }
}
