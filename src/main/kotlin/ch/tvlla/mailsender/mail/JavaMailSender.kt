package ch.tvlla.mailsender.mail

import org.springframework.context.annotation.Bean
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import java.util.*

/*
@Bean
fun getJavaMailSender(): JavaMailSender? {
    val mailSender = JavaMailSenderImpl()
    mailSender.host = "smtp.gmail.com"
    mailSender.port = 587
    mailSender.username = "tvl.leichtathletik.bern@gmail.com"
    mailSender.password = "phxfmflgupsbmsfw"
    val props: Properties = mailSender.javaMailProperties
    props["mail.transport.protocol"] = "smtp"
    props["mail.smtp.auth"] = "true"
    props["mail.smtp.starttls.enable"] = "true"
    props["mail.debug"] = "true"
    return mailSender
}

 */