package ch.tvlla.mailsender


import ch.tvlla.mailsender.resultCollector.ResultService
import org.springframework.beans.factory.InitializingBean
import org.springframework.boot.autoconfigure.SpringBootApplication

import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.stereotype.Component
import javax.inject.Inject

@SpringBootApplication
@EnableCaching
class MailSenderApplication
    fun main(args: Array<String>) {
        runApplication<MailSenderApplication>(*args)
    }


@Component
class StartupRunner: InitializingBean {

    @Inject
    private lateinit var resultService: ResultService

    override fun afterPropertiesSet() {
        resultService.startCollecting()
    }

}



