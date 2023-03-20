package io.searching.server.integration.blog

interface BlogSearcher {

    /**
     * 블로그 검색
     *
     * keyword : 검색 가능
     * sortType : ACCURACY, RECENCY 지원
     * page : pagination 형태로 제공
     */
    fun search(keyword: String, sortType: SortType, page: Int): Triple<Int, Boolean, List<Document>>

    companion object {
        const val PAGE_DISPLAY_CONTENTS_COUNT = 10
    }
}
