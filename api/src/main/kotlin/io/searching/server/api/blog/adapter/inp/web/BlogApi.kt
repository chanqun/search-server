package io.searching.server.api.blog.adapter.inp.web

import io.searching.server.api.blog.service.BlogAppService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/blogs")
class BlogApi(
    private val blogAppService: BlogAppService
) {

    @GetMapping
    fun search(@Valid req: BlogSearchReq): ResponseEntity<BlogSearchRes> {
        val (page, isEnd, documents) = with(req) { blogAppService.search(keyword, sort, page) }

        return ResponseEntity.ok(BlogSearchRes.of(page, isEnd, documents))
    }
}
