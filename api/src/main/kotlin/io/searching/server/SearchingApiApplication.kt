package io.searching.server

import io.searching.server.integration.blog.BlogProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(value = [BlogProperties::class])
class SearchingApiApplication

fun main(args: Array<String>) {
    runApplication<SearchingApiApplication>(*args)
}
