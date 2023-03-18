package io.searching.server.integration.blog.kakao

import io.searching.server.integration.blog.BlogProperties
import io.searching.server.integration.blog.BlogSearcher
import io.searching.server.integration.blog.Documents
import io.searching.server.integration.blog.SortType
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class KakaoBlogSearcher(
    private val kakaoBlogClient: KakaoBlogClient,
    private val blogProperties: BlogProperties
) : BlogSearcher {
    override fun search(keyword: String, sort: SortType?, page: Int?): Page<Documents> {
        val kakaoBlogSearchRes =
            kakaoBlogClient.search(keyword, sort, page, authorization = "KakaoAK ${blogProperties.kakaoRestApiKey}")

        println(kakaoBlogSearchRes)

        return kakaoBlogSearchRes.toPage()
    }
}
