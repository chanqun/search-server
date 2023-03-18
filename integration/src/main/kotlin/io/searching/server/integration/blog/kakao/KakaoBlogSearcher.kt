package io.searching.server.integration.blog.kakao

import io.searching.server.integration.blog.BlogSearcher
import io.searching.server.integration.blog.Documents
import io.searching.server.integration.blog.SortType
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class KakaoBlogSearcher(
    private val kakaoClient: KakaoClient
) : BlogSearcher {
    override fun search(keyword: String, sort: SortType, page: Int): Page<Documents> {
        val value = kakaoClient.searchBlog(keyword, sort, page)

        return Page.empty()
    }
}
