package io.searching.server.api.blog.adapter.inp.web

import io.searching.server.integration.blog.BlogSearcher
import jakarta.validation.Valid
import org.springframework.context.ApplicationEventPublisher
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/blogs")
class BlogApi(
    private val blogSearcher: BlogSearcher,
    private val eventPublisher: ApplicationEventPublisher
) {

    @GetMapping
    fun search(@Valid req: BlogSearchReq): ResponseEntity<BlogSearchRes> {
        val (page, isEnd, documents) = with(req) { blogSearcher.search(keyword, sort, page) }

        eventPublisher.publishEvent(BlogSearchedEvent(req.keyword))

        return ResponseEntity.ok(BlogSearchRes(page, isEnd, documents))
    }
}
