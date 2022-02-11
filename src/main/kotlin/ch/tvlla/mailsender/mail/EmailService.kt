package ch.tvlla.mailsender.mail

import ch.tvlla.mailsender.document.UploadModel
import java.io.File
import java.io.IOException
import javax.mail.MessagingException


interface EmailService {
    fun sendSimpleMessage(
        to: String?,
        subject: String?,
        text: String?
    )

    fun sendSimpleMessageUsingTemplate(
        to: String?,
        subject: String?,
        vararg templateModel: String?
    )

    fun sendMessageWithAttachment(
        to: String,
        content: UploadModel,
        attachment: File,
        filename: String
    )

    @Throws(IOException::class, MessagingException::class)
    fun sendMessageUsingThymeleafTemplate(
        to: String?,
        subject: String?,
        templateModel: Map<String?, Any?>?
    )

}