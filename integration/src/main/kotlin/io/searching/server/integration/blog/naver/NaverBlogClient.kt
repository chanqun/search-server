package io.searching.server.integration.blog.naver

import io.searching.server.integration.blog.BlogSearcher.Companion.PAGE_DISPLAY_CONTENTS_COUNT
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "naverBlogClient", url = "\${blog.naverUrl}")
interface NaverBlogClient {

    @GetMapping(value = ["/v1/search/blog"])
    fun search(
        @RequestParam("query") keyword: String,
        @RequestParam("sort") sort: String?,
        @RequestParam("start") start: Int?,
        @RequestParam("display") size: Int = PAGE_DISPLAY_CONTENTS_COUNT,
        @RequestHeader("X-Naver-Client-Id") clientId: String,
        @RequestHeader("X-Naver-Client-Secret") clientSecret: String
    ): NaverBlogSearchRes
}
