package io.searching.server.integration.blog

interface BlogSearchVendor {

    fun search(keyword: String, sortType: SortType?, page: Int): Triple<Int, Boolean, List<Document>>?
}
