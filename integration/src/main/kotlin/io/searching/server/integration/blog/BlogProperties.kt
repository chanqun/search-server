package io.searching.server.integration.blog

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "blog")
class BlogProperties(
    val kakaoUrl: String,
    val kakaoRestApiKey: String,
    val naverUrl: String,
    val naverClientId: String,
    val naverClientSecret: String
)
