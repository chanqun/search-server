package io.searching.server.core.searchranking.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SearchRankingTest {

    @Test
    fun `검색어 랭킹 생성`() {
        val searchRanking = SearchRanking("검색어")

        assertThat(searchRanking.keyword).isEqualTo("검색어")
        assertThat(searchRanking.count).isEqualTo(0)
        assertThat(searchRanking.createdAt).isNotNull
    }

    @Test
    fun `검색하면 검색 횟수가 올라간다`() {
        val searchRanking = createSearchRanking(count = 1)

        searchRanking.record()

        assertThat(searchRanking.count).isEqualTo(2)
    }
}

fun createSearchRanking(
    keyword: String = "검색어",
    count: Int = 0
): SearchRanking {
    return SearchRanking(keyword, count)
}
