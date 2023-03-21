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
internal class RecordSearchRankingTest @Autowired constructor(
    private val recordSearchRanking: RecordSearchRanking,
    private val searchRankingRepository: SearchRankingRepository
) {

    @Test
    fun `첫 검색어인 경우 검색어 랭킹 등록 후 검색 횟수를 올린다`() {
        assertThat(searchRankingRepository.findAll()).isEmpty()

        val searchRanking = recordSearchRanking.record(RecordSearchRankingCommand("검색어", 1))

        assertThat(searchRanking.count).isEqualTo(1)
        assertThat(searchRankingRepository.findAll()).hasSize(1)
    }

    @Test
    fun `이미 등록된 검색어인 경우 조회 후 검색 횟수를 변경한다`() {
        searchRankingRepository.save(createSearchRanking("검색어", count = 10))

        val searchRanking = recordSearchRanking.record(RecordSearchRankingCommand("검색어", 11))

        assertThat(searchRanking.count).isEqualTo(11)
        assertThat(searchRankingRepository.findAll()).hasSize(1)
    }
}
