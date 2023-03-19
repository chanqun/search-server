package io.searching.server.core.searchranking.application.service

import io.searching.server.core.searchranking.application.port.inp.RecordSearchRankingCommand
import io.searching.server.core.searchranking.application.port.inp.RecordSearchRanking
import io.searching.server.core.searchranking.application.port.outp.SearchRankingRepository
import io.searching.server.core.searchranking.domain.SearchRanking
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SearchRankingService(
    private val searchRankingRepository: SearchRankingRepository
) : RecordSearchRanking {
    override fun record(command: RecordSearchRankingCommand): SearchRanking {
        val searchRanking = searchRankingRepository.findByKeyword(command.keyword)
            ?: let { searchRankingRepository.save(SearchRanking(command.keyword)) }

        return searchRanking.search()
    }
}
