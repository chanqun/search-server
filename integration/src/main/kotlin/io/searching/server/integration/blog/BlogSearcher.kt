package io.searching.server.integration.blog

import org.springframework.data.domain.Page

interface BlogSearcher {

    /**
     * 블로그 검색
     *
     * keyword : 검색 가능
     * sort : ACCURACY, RECENCY 지원
     * page : pagination 형태로 제공
     */
    fun search(keyword: String, sort: SortType, page: Int): Page<Documents>
}
