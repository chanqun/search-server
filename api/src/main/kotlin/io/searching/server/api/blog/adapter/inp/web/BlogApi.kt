package io.searching.server.api.blog.adapter.inp.web

import io.searching.server.api.blog.service.BlogAppService
import jakarta.validation.Valid
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger { }

@RestController
@RequestMapping("/blogs")
class BlogApi(
    private val blogAppService: BlogAppService
) {

    @GetMapping
    fun search(@Valid req: BlogSearchReq): ResponseEntity<BlogSearchRes> {
        logger.info { "[GET] /blogs, $req" }

        val (page, isEnd, documents) = with(req) { blogAppService.search(keyword, sort, page) }

        return ResponseEntity.ok(BlogSearchRes.of(page, isEnd, documents))
    }
}
