package io.searching.server.api.searchranking.adapter.inp.event

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.CountDownLatch

@SpringBootTest
@Transactional
internal class KeywordCounterTest @Autowired constructor(
    private val keywordCounter: KeywordCounter
) {
    @Test
    fun `키워드 카운트 증가 동시성 확인`() {
        val latch = CountDownLatch(100)

        (1..100).forEach { _ ->
            Thread {
                latch.countDown()

                keywordCounter.increase("keyword")
            }.start()
        }
        latch.await()

        assertThat(keywordCounter.get("keyword")).isEqualTo(100)
    }
}
