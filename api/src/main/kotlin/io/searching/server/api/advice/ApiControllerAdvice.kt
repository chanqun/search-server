package io.searching.server.api.advice

import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

private val logger = KotlinLogging.logger {}

@RestControllerAdvice
class ApiControllerAdvice {
    @ExceptionHandler(Exception::class)
    fun exception(e: Exception): ResponseEntity<ErrorRes> {
        logger.error { e.message }

        return ResponseEntity.internalServerError().body(ErrorRes(UNKNOWN_ERROR_MESSAGE))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ErrorRes> {
        logger.info { "MethodArgumentNotValidException > ${e.message}" }

        return ResponseEntity.badRequest().body(ErrorRes(DEFAULT_REQUEST_ERROR_MESSAGE))
    }

    @ExceptionHandler(BindException::class)
    fun bindException(e: BindException): ResponseEntity<ErrorRes> {
        logger.info { "BindException > ${e.message}" }

        val fieldErrors = e.bindingResult.fieldErrors

        val errors: Any = if (fieldErrors.isEmpty()) {
            DEFAULT_REQUEST_ERROR_MESSAGE
        } else {
            fieldErrors.associate {
                it.field to it.defaultMessage.toString()
            }
        }

        return ResponseEntity.badRequest().body(ErrorRes(errors))
    }

    companion object {
        const val DEFAULT_REQUEST_ERROR_MESSAGE = "요청 값을 확인해주세요"
        const val UNKNOWN_ERROR_MESSAGE = "서버에 문제가 발생 했습니다. 잠시 후 다시 시도해주세요"
    }
}

class ErrorRes(
    var errors: Any
)
