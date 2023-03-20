package io.searching.server.core.searchranking.application.port.inp

import io.searching.server.core.searchranking.domain.SearchRanking

interface GetTopTenSearchRanking {

    /**
     * 사용자들이 많이 검색한 순서대로, 최대 10개의 검색 키워드를 제공
     */
    fun get(): List<SearchRanking>
}
