package io.searching.server.core.searchranking.application.port.outp

import io.searching.server.core.searchranking.domain.SearchRanking
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface SearchRankingRepository : JpaRepository<SearchRanking, Long> {

    fun findByKeyword(keyword: String): SearchRanking?

    @Query(
        "select r from SearchRanking r order by r.count desc"
    )
    fun findTopRankingByCount(pageable: Pageable): List<SearchRanking>
}
