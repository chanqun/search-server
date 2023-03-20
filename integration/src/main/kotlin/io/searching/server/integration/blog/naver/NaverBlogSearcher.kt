package io.searching.server.integration.blog.naver

import io.searching.server.integration.blog.*
import io.searching.server.integration.blog.BlogSearcher.Companion.PAGE_DISPLAY_CONTENTS_COUNT
import mu.KotlinLogging
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger { }

@Order(1)
@Component
class NaverBlogSearcher(
    private val naverBlogClient: NaverBlogClient,
    private val blogProperties: BlogProperties
) : BlogSearchVendor {
    override fun search(keyword: String, sortType: SortType?, page: Int): Triple<Int, Boolean, List<Document>>? {
        return try {
            val res: NaverBlogSearchRes =
                naverBlogClient.search(
                    keyword, sortType?.let { convertSortTypeToString(it) }, calcContentStart(page),
                    clientId = blogProperties.naverClientId,
                    clientSecret = blogProperties.naverClientSecret
                )

            Triple(page, res.isEnd, res.items.map { it.toDocument() })
        } catch (e: Exception) {
            logger.error { "NaverBlogSearcher ${e.message}" }

            null
        }
    }

    private fun calcContentStart(page: Int) = (page * PAGE_DISPLAY_CONTENTS_COUNT) + 1

    private fun convertSortTypeToString(sortType: SortType): String {
        return when (sortType) {
            SortType.ACCURACY -> "sim"
            SortType.RECENCY -> "date"
        }
    }
}
