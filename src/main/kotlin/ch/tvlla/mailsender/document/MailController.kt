package ch.tvlla.mailsender.document

import ch.tvlla.mailsender.utils.Util
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletRequest

@RestController
class MailController(private val request: HttpServletRequest, private val mailService: MailService /*  private val mailService: EmailServiceImpl*/) {

    private val logger: Logger = LoggerFactory.getLogger(MailController::class.java)

    @Value("\${custom.mail.target}")
    private val targetAddr = ""

    @GetMapping(value = ["/send"])
    fun sendMail(){
        Util.requestLogger(logger, request)
      //  mailService.sendSimpleMessage(targetAddr, "hello wööörld", "yay!")
    }

    @PostMapping(value = ["/send"])
    fun sendAttachment(@RequestParam("file") attachment: MultipartFile): ResponseEntity<String> {
        Util.requestLogger(logger, request)

        mailService.sendMailWithAttachment(attachment, request.remoteAddr)
        return ResponseEntity("hi", HttpStatus.OK)
    }

    @GetMapping(value = ["/test"])
    fun testFunc(): ResponseEntity<String>{
        println(request.remoteAddr)
        return ResponseEntity("hellöu wöörld", HttpStatus.OK)
    }

}