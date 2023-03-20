package io.searching.server.api.config

import mu.KotlinLogging
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

@Configuration
@EnableAsync
class AsyncConfig : AsyncConfigurer {
    val logger = KotlinLogging.logger {}

    override fun getAsyncExecutor(): Executor =
        ThreadPoolTaskExecutor().apply {
            corePoolSize = 5
            maxPoolSize = 50
            queueCapacity = 100
            setThreadNamePrefix("ApiExecutor-")
            initialize()
        }

    override fun getAsyncUncaughtExceptionHandler(): AsyncUncaughtExceptionHandler =
        AsyncUncaughtExceptionHandler { e, _, _ ->
            logger.error(e) { e.message }
        }
}
