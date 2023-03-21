package io.searching.server.core.searchranking.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
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
    fun `검색 횟수를 기록한다`() {
        val searchRanking = createSearchRanking(count = 1)

        searchRanking.record(10)

        assertThat(searchRanking.count).isEqualTo(10)
    }

    @Test
    fun `음수는 검색 횟수 기록할 수 없다`() {
        val searchRanking = createSearchRanking(count = 1)

        assertThatIllegalArgumentException().isThrownBy {
            searchRanking.record(-1)
        }
    }
}

fun createSearchRanking(
    keyword: String = "검색어",
    count: Int = 0
): SearchRanking {
    return SearchRanking(keyword, count)
}
