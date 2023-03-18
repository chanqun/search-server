package io.searching.server.integration.blog.kakao

import io.searching.server.integration.blog.SortType
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "kakaoBlogClient", url = "\${blog.kakaoUrl}")
interface KakaoBlogClient {

    @GetMapping(value = ["/v2/search/blog"], produces = ["application/json;charset=utf-8"])
    fun search(
        @RequestParam("query") keyword: String,
        @RequestParam("sort") sortType: SortType,
        @RequestParam page: Int,
        @RequestParam size: Int = 10,
        @RequestHeader("Authorization") authorization: String
    ): String
}
