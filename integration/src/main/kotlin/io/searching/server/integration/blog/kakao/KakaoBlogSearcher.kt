package io.searching.server.integration.blog.kakao

import io.searching.server.integration.blog.*
import io.searching.server.integration.blog.DefaultBlogSearcher.Companion.PAGE_DISPLAY_CONTENTS_COUNT
import mu.KotlinLogging
import org.springframework.context.ApplicationEventPublisher
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Order(0)
@Component
class KakaoBlogSearcher(
    private val kakaoBlogClient: KakaoBlogClient,
    private val blogProperties: BlogProperties,
    private val eventPublisher: ApplicationEventPublisher
) : BlogSearchVendor {
    override fun search(keyword: String, sortType: SortType, page: Int): Triple<Int, Boolean, List<Document>>? {
        return try {
            val res: KakaoBlogSearchRes =
                kakaoBlogClient.search(
                    keyword, sortType, page,
                    size = PAGE_DISPLAY_CONTENTS_COUNT, authorization = "KakaoAK ${blogProperties.kakaoRestApiKey}"
                )

            Triple(page, res.meta.is_end, res.documents.map { it.toDocument() })
        } catch (e: Exception) {
            logger.error(e) { "KakaoBlogSearcher ${e.message}" }

            eventPublisher.publishEvent(BlogSearchingFailedEvent())

            null
        }
    }
}
