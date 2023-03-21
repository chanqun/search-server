package io.searching.server.api.advice

import io.searching.server.core.support.exception.CustomSearchingException
import mu.KotlinLogging
import org.springframework.context.ApplicationEventPublisher
import org.springframework.http.HttpStatus
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

private val logger = KotlinLogging.logger {}

@RestControllerAdvice
class ApiControllerAdvice(
    private val eventPublisher: ApplicationEventPublisher
) {
    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun exception(e: Exception): ErrorRes {
        logger.error(e) { e.message }

        eventPublisher.publishEvent(UnhandledExceptionEvent(e))

        return ErrorRes(UNKNOWN_ERROR_MESSAGE)
    }

    @ExceptionHandler(CustomSearchingException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun customSearchingException(e: CustomSearchingException): ErrorRes {
        logger.error(e) { "CustomSearchingException: ${e.error}" }

        return ErrorRes(e.error)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException): ErrorRes {
        logger.trace(e) { "MethodArgumentNotValidException > ${e.message}" }

        return ErrorRes(DEFAULT_REQUEST_ERROR_MESSAGE)
    }

    @ExceptionHandler(BindException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun bindException(e: BindException): ErrorRes {
        logger.trace(e) { "BindException > ${e.message}" }

        val fieldErrors = e.bindingResult.fieldErrors

        val errors: Any = if (fieldErrors.isEmpty()) {
            DEFAULT_REQUEST_ERROR_MESSAGE
        } else {
            fieldErrors.associate {
                it.field to it.defaultMessage.toString()
            }
        }

        return ErrorRes(errors)
    }

    companion object {
        const val DEFAULT_REQUEST_ERROR_MESSAGE = "요청 값을 확인해주세요"
        const val UNKNOWN_ERROR_MESSAGE = "서버에 문제가 발생 했습니다. 잠시 후 다시 시도해주세요"
    }
}

class UnhandledExceptionEvent(e: Exception)

class ErrorRes(
    var errors: Any
)
