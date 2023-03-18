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

    @ExceptionHandler(Exception::class)
    fun exception(ex: Exception, req: HttpServletRequest): String {
        logger.error(ex) { ex.message }

        throw ex
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException::class)
    fun bindException(e: BindException): ResponseEntity<Map<String, String>> {
        logger.info { "BindException > ${e.message}" }

        val fieldErrors = e.bindingResult.fieldErrors

        val errors = if (fieldErrors.isEmpty()) {
            mapOf(DEFAULT_ERROR_TITLE to DEFAULT_REQUEST_ERROR_MESSAGE)
        } else {
            fieldErrors.associate { it.field to it.defaultMessage.toString() }
        }

        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

    companion object {
        const val DEFAULT_ERROR_TITLE = "error"
        const val DEFAULT_REQUEST_ERROR_MESSAGE = "요청 값을 확인해주세요"
    }
}
