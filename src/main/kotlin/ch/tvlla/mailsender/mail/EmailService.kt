package ch.tvlla.mailsender.mail

import java.io.File
import java.io.IOException
import javax.mail.MessagingException


/**
 * Created by Olga on 8/22/2016.
 */
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
        subject: String,
        text: String,
        attachment: File,
        filename: String
    )

    @Throws(IOException::class, MessagingException::class)
    fun sendMessageUsingThymeleafTemplate(
        to: String?,
        subject: String?,
        templateModel: Map<String?, Any?>?
    )
/*
    @Throws(IOException::class, TemplateException::class, MessagingException::class)
    fun sendMessageUsingFreemarkerTemplate(
        to: String?,
        subject: String?,
        templateModel: Map<String?, Any?>?
    )

 */
}