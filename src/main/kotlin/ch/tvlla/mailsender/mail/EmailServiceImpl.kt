package ch.tvlla.mailsender.mail

import ch.tvlla.mailsender.document.UploadModel
import ch.tvlla.mailsender.utils.Util
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.FileSystemResource
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.mail.javamail.MimeMessagePreparator
import org.springframework.stereotype.Component
import java.io.File
import javax.mail.Message
import javax.mail.internet.InternetAddress


@Component
class EmailServiceImpl : EmailService {
    @Autowired
    private lateinit var emailSender: JavaMailSender

     override fun sendSimpleMessage(
        to: String?, subject: String?, text: String?
    ) {
        val message = SimpleMailMessage()
        message.setFrom("test@helloWorld.com")
        message.setTo(to)
        message.setSubject(subject!!)
        message.setText(text!!)
        emailSender.send(message)
    }

    override fun sendSimpleMessageUsingTemplate(to: String?, subject: String?, vararg templateModel: String?) {
        TODO("Not yet implemented")
    }

    override fun sendMessageUsingThymeleafTemplate(to: String?, subject: String?, templateModel: Map<String?, Any?>?) {
        TODO("Not yet implemented")
    }

    override fun sendMessageWithAttachment(to: String, content: UploadModel, attachment: File, filename: String){

        val message = MimeMessagePreparator(){
            it.setRecipient(Message.RecipientType.TO, InternetAddress(to))
            it.setFrom(InternetAddress("test@helloWorld.com"))
            it.subject = "File upload from ${content.name}"

            val helper = MimeMessageHelper(it, true)
            val file = FileSystemResource(attachment)
            helper.addAttachment(filename, file)
            helper.setText(Util.getMailText(content), true)
        }
     emailSender.send(message)
    }


}