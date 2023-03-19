package io.searching.server.core.searchranking.application.port.outp

import io.searching.server.core.searchranking.domain.SearchRanking
import org.springframework.data.jpa.repository.JpaRepository

interface SearchRankingRepository : JpaRepository<SearchRanking, Long> {

    fun findByKeyword(keyword: String): SearchRanking?
}
