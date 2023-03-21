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
    override fun search(keyword: String, sortType: SortType, page: Int): BlogSearcherDto? {
        return runCatching {
            val res: KakaoBlogSearchRes =
                kakaoBlogClient.search(
                    keyword, sortType, page,
                    size = PAGE_DISPLAY_CONTENTS_COUNT, authorization = "KakaoAK ${blogProperties.kakaoRestApiKey}"
                )

            BlogSearcherDto(page, res.meta.isEnd, res.documents.map { it.toDocument() })
        }.onFailure {
            logger.error(it) { "KakaoBlogSearcher ${it.message}" }

            eventPublisher.publishEvent(BlogSearchingFailedEvent())
        }.getOrNull()
    }
}
