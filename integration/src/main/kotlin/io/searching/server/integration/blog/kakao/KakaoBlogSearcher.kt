package io.searching.server.integration.blog.kakao

import io.searching.server.integration.blog.*
import mu.KotlinLogging
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Order(0)
@Component
class KakaoBlogSearcher(
    private val kakaoBlogClient: KakaoBlogClient,
    private val blogProperties: BlogProperties
) : BlogSearchVendor {
    override fun search(keyword: String, sortType: SortType, page: Int): Triple<Int, Boolean, List<Document>>? {
        return try {
            val res: KakaoBlogSearchRes =
                kakaoBlogClient.search(keyword, sortType, page, authorization = "KakaoAK ${blogProperties.kakaoRestApiKey}")

            Triple(page, res.meta.is_end, res.documents.map { it.toDocument() })
        } catch (e: Exception) {
            logger.error { "KakaoBlogSearcher ${e.message}" }

            null
        }
    }
}
