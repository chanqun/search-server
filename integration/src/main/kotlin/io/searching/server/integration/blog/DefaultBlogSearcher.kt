package io.searching.server.integration.blog

import org.springframework.stereotype.Component

@Component
class DefaultBlogSearcher(
    private val vendors: List<BlogSearchVendor>
) : BlogSearcher {
    override fun search(keyword: String, sortType: SortType, page: Int): Triple<Int, Boolean, List<Document>> {
        val res: Triple<Int, Boolean, List<Document>>? = vendors.firstNotNullOfOrNull {
            it.search(keyword, sortType, page)
        }

        return res ?: throw IllegalArgumentException()
    }

    companion object {
        const val PAGE_DISPLAY_CONTENTS_COUNT = 10
    }
}
