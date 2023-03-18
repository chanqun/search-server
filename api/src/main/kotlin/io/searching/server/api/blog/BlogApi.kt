package io.searching.server.api.blog

import io.searching.server.integration.blog.BlogSearcher
import io.searching.server.integration.blog.SortType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/blogs")
class BlogApi(
    private val blogSearcher: BlogSearcher
) {

    @GetMapping
    fun search() {
        blogSearcher.search("hello", SortType.ACCURACY, 3)
    }
}
