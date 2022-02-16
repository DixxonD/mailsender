import ch.tvlla.mailsender.mailsender.document.UploadModel
import ch.tvlla.mailsender.mailsender.document.MailService
import ch.tvlla.mailsender.mailsender.utils.Util
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletRequest

@Controller
class MailController(private val request: HttpServletRequest, private val mailService: MailService /*  private val mailService: EmailServiceImpl*/) {

    private val logger: Logger = LoggerFactory.getLogger(MailController::class.java)

    @PostMapping(value = ["/send"])
    fun sendAttachment(data: UploadModel, @RequestParam("file") attachment: MultipartFile): ResponseEntity<String> {
        Util.requestLogger(logger, request)
        mailService.sendMailWithAttachment(attachment,data, request.remoteAddr)
        return ResponseEntity("Erfolgreich hochgeladen.", HttpStatus.OK)
    }

    @GetMapping(value = ["/form"])
    fun sendFormular(): String{
        return "sendFormular"
    }

}