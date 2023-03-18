package io.searching.server.api.advice

import jakarta.servlet.http.HttpServletRequest
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

private val logger = KotlinLogging.logger {}

@RestControllerAdvice
class ApiControllerAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun exception(e: Exception, req: HttpServletRequest): ResponseEntity<String> {
        logger.error { e.message }

        return ResponseEntity(UNKNOWN_ERROR, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException::class)
    fun bindException(e: BindException): ResponseEntity<Any> {
        logger.info { "BindException > ${e.message}" }

        val fieldErrors = e.bindingResult.fieldErrors

        val errors: Any = if (fieldErrors.isEmpty()) {
            DEFAULT_REQUEST_ERROR_MESSAGE
        } else {
            fieldErrors.associate { it.field to it.defaultMessage.toString() }
        }

        return ResponseEntity(mapOf(DEFAULT_ERROR_TITLE to errors), HttpStatus.BAD_REQUEST)
    }

    companion object {
        const val DEFAULT_ERROR_TITLE = "errors"
        const val DEFAULT_REQUEST_ERROR_MESSAGE = "요청 값을 확인해주세요"
        const val UNKNOWN_ERROR = "서버에 문제가 발생 했습니다. 잠시 후 다시 시도해주세요"
    }
}
