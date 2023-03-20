package io.searching.server.core.support.exception

import io.searching.server.core.support.exception.SearchingError.*

class CustomSearchingException(val error: SearchingError = UNKNOWN) : RuntimeException(error.name)

enum class SearchingError {
    UNKNOWN,
    SEARCHING_SERVICE_ERROR
}
