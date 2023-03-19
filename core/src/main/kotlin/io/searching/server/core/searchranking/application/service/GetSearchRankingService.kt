package io.searching.server.core.searchranking.application.service

import io.searching.server.core.searchranking.application.port.inp.GetSearchRanking
import io.searching.server.core.searchranking.application.port.outp.SearchRankingRepository
import io.searching.server.core.searchranking.domain.SearchRanking
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class GetSearchRankingService(
    private val searchRankingRepository: SearchRankingRepository
) : GetSearchRanking {
    override fun getTopTen(): List<SearchRanking> {
        return searchRankingRepository.findTopRankingByCount(Pageable.ofSize(MAX_RANKING_REQUEST))
    }

    companion object {
        const val MAX_RANKING_REQUEST = 10
    }
}
