package io.searching.server.api.blog.adapter.inp.web

import io.searching.server.api.blog.service.BlogAppService
import javax.validation.Valid
import mu.KotlinLogging
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
    fun search(@Valid req: BlogSearchReq): BlogSearchRes {
        logger.info { "[GET] /blogs, $req" }

        val blogSearcherDto = blogAppService.search(req.toSpec())

        return BlogSearchRes.of(blogSearcherDto)
    }
}
