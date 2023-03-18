package io.searching.server.api.blog.adapter.inp.web

import io.searching.server.integration.blog.BlogSearcher
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/blogs")
class BlogApi(
    private val blogSearcher: BlogSearcher
) {

    @GetMapping
    fun search(@Valid req: BlogSearchReq) {
        with(req) { blogSearcher.search(keyword, sort, page) }
    }
}
