package io.searching.server.core.searchranking.application.port.outp

import io.searching.server.core.searchranking.domain.createSearchRanking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.Pageable

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
internal class SearchRankingRepositoryTest @Autowired constructor(
    private val searchRankingRepository: SearchRankingRepository
) {

    @Test
    fun `count가 높은 순으로 조회한다`() {
        (1..13).forEach {
            searchRankingRepository.save(createSearchRanking("keyword$it", count = it))
        }

        val searchRankings = searchRankingRepository.findTopRankingByCount(Pageable.ofSize(3)).map { it.keyword }

        assertThat(searchRankings).containsExactly("keyword13", "keyword12", "keyword11")
    }
}
