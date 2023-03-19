package io.searching.server.core.searchranking.application.service

import io.searching.server.core.searchranking.application.port.inp.FindOrCreateSearchRanking
import io.searching.server.core.searchranking.application.port.inp.FindOrCreateSearchRankingCommand
import io.searching.server.core.searchranking.application.port.outp.SearchRankingRepository
import io.searching.server.core.searchranking.domain.SearchRanking
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SearchRankingService(
    private val searchRankingRepository: SearchRankingRepository
) : FindOrCreateSearchRanking {
    override fun findOrCreate(command: FindOrCreateSearchRankingCommand): SearchRanking {
        return searchRankingRepository.findByKeyword(command.keyword) ?: let {
            searchRankingRepository.save(SearchRanking(command.keyword))
        }
    }
}
