package io.sharing.server.core.support.exception

import io.sharing.server.core.support.exception.SearchingError.*

class CustomSearchingException(val error: SearchingError = UNKNOWN) : RuntimeException(error.name)

enum class SearchingError {
    UNKNOWN
}
