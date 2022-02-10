package ch.tvlla.mailsender.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {


    @ExceptionHandler(value = [CustomException::class])
    fun customExceptions(ex: CustomException): ResponseEntity<String> {
        logger.warn(ex.message)
        return ResponseEntity<String>(ex.message, HttpStatus.BAD_REQUEST)
    }


    }
