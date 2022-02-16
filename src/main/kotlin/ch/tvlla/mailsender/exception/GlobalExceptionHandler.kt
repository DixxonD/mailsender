package ch.tvlla.exception

import ch.tvlla.mailsender.mailsender.utils.FileSize
import ch.tvlla.mailsender.mailsender.utils.Unit
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.multipart.MaxUploadSizeExceededException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {


    @ExceptionHandler(value = [CustomException::class])
    fun customExceptions(ex: CustomException): ResponseEntity<String> {
        logger.warn(ex.message)
        return ResponseEntity<String>(ex.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [MaxUploadSizeExceededException::class])
    fun handleMaxUploadSizeExceededException(): ResponseEntity<String>{
        val message = "Die hochgeladene Datei ist zu gross. Maximal erlaubt sind ${FileSize(20, Unit.MB)}"
        logger.warn(message)
        return ResponseEntity<String>(message, HttpStatus.BAD_REQUEST)
    }


}
