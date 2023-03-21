package io.searching.server.api.blog.service

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import io.searching.server.integration.blog.BlogSearcher
import io.searching.server.integration.blog.BlogSearcherDto
import io.searching.server.integration.blog.Document
import io.searching.server.integration.blog.SortType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.event.ApplicationEvents
import org.springframework.test.context.event.RecordApplicationEvents
import org.springframework.transaction.annotation.Transactional
import java.time.OffsetDateTime

@SpringBootTest
@Transactional
@RecordApplicationEvents
internal class BlogAppServiceTest @Autowired constructor(
    private val blogAppService: BlogAppService
) {

    @Autowired
    lateinit var applicationEvents: ApplicationEvents

    @MockkBean
    lateinit var blogSearcher: BlogSearcher

    @Test
    fun `블로그 검색시 블로그 검색 이벤트 발생`() {
        every { blogSearcher.search("keyword", any(), any()) } returns BlogSearcherDto(
            0, true, listOf(
                Document(
                    title = "title", contents = "contents", url = "url", blogName = "blogName", thumbnail = "thumbnail",
                    OffsetDateTime.now()
                )
            )
        )

        blogAppService.search(BlogSearchSpec("keyword", SortType.ACCURACY, page = 1))

        assertThat(applicationEvents.stream(BlogSearchedEvent::class.java).toList().first().keyword).isEqualTo("keyword")
        verify(exactly = 1) { blogSearcher.search("keyword", any(), any()) }
    }
}
