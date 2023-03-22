package io.searching.server.api.blog.service

import io.searching.server.core.support.exception.CustomSearchingException
import io.searching.server.integration.blog.SortType
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class BlogSearchSpecTest {

    @Test
    fun `키워드가 공백이면 예외 발생`() {
        assertThatThrownBy {
            BlogSearchSpec(
                "", sortType = SortType.ACCURACY, 1
            )
        }.isInstanceOf(CustomSearchingException::class.java)
    }
}
