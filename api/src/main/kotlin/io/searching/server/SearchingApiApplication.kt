package io.searching.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class SearchingApiApplication

fun main(args: Array<String>) {
    runApplication<SearchingApiApplication>(*args)
}
