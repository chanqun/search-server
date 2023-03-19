package io.searching.server.core.searchranking.application.port.inp

import io.searching.server.core.searchranking.application.port.outp.SearchRankingRepository
import io.searching.server.core.searchranking.domain.createSearchRanking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
internal class GetSearchRankingTest @Autowired constructor(
    private val searchRankingRepository: SearchRankingRepository,
    private val getSearchRanking: GetSearchRanking
) {

    @Test
    fun `최대 11개 이상이 있어도 최대 10개 조회한다`() {
        (1..13).forEach {
            searchRankingRepository.save(createSearchRanking("keyword$it"))
        }

        val searchRankings = getSearchRanking.getTopTen()

        assertThat(searchRankings).hasSize(10)
    }
}
