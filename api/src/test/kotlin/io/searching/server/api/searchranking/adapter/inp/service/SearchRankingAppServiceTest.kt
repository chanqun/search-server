package io.searching.server.api.searchranking.adapter.inp.service

import io.searching.server.api.searchranking.adapter.inp.event.KeywordCounter
import io.searching.server.core.searchranking.application.port.outp.SearchRankingRepository
import io.searching.server.core.searchranking.domain.SearchRanking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
internal class SearchRankingAppServiceTest @Autowired constructor(
    private val keywordCounter: KeywordCounter,
    private val searchRankingRepository: SearchRankingRepository,
    private val searchRankingAppService: SearchRankingAppService
) {
    @BeforeEach
    fun setUp() {
        (1..2).forEach {
            searchRankingRepository.save(SearchRanking("keyword$it", it))
        }

        keywordCounter.initialize()
    }

    @Test
    fun `검색어 순위 랭킹 조회`() {
        val rankingList = searchRankingAppService.getTopTen()

        assertThat(rankingList).containsExactly(Pair("keyword2", 2), Pair("keyword1", 1))
    }

    @Test
    fun `검색어 순위 저장`() {
        keywordCounter.increase("keyword1", 1)
        keywordCounter.increase("keyword2", 1)

        searchRankingAppService.recordSearchRanking()

        assertThat(searchRankingRepository.findByKeyword("keyword1")!!.count).isEqualTo(2)
        assertThat(searchRankingRepository.findByKeyword("keyword2")!!.count).isEqualTo(3)
    }
}
