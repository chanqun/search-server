package io.searching.server.api.blog.adapter.inp.web

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import io.searching.server.integration.blog.BlogSearcher
import io.searching.server.integration.blog.BlogSearcherDto
import io.searching.server.integration.blog.DefaultBlogSearcher.Companion.PAGE_DISPLAY_CONTENTS_COUNT
import io.searching.server.integration.blog.Document
import io.searching.server.integration.blog.SortType
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.transaction.annotation.Transactional
import java.time.OffsetDateTime

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
internal class BlogApiTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockkBean
    lateinit var blogSearcher: BlogSearcher

    @Test
    fun `blog 키워드 조회`() {
        every { blogSearcher.search("keyword", any(), any()) } returns BlogSearcherDto(
            1, SortType.ACCURACY, PAGE_DISPLAY_CONTENTS_COUNT, 1, listOf(
                Document(
                    title = "title", contents = "contents", url = "url", blogName = "blogName", thumbnail = "thumbnail",
                    OffsetDateTime.now()
                )
            )
        )

        mockMvc.perform(get("/blogs").param("keyword", "keyword"))
            .andExpectAll(
                status().isOk,
                jsonPath("$.pageInfo.page").value(1),
                jsonPath("$.pageInfo.sort").value(SortType.ACCURACY.name),
                jsonPath("$.pageInfo.displayCount").value(PAGE_DISPLAY_CONTENTS_COUNT),
                jsonPath("$.pageInfo.totalCount").value(1),
                jsonPath("$.pageInfo.isEnd").value(true),
                jsonPath("$.documents[0].title").value("title"),
                jsonPath("$.documents[0].contents").value("contents"),
                jsonPath("$.documents[0].url").value("url"),
                jsonPath("$.documents[0].blogName").value("blogName"),
                jsonPath("$.documents[0].thumbnail").value("thumbnail"),
                jsonPath("$.documents[0].postedAt").isNotEmpty
            )

        verify(exactly = 1) { blogSearcher.search("keyword", any(), any()) }
    }
}
