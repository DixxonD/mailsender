package ch.tvlla.mailsender.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
class CustomAuthenticationEntryPoint : AuthenticationEntryPoint {
    override fun commence(p0: HttpServletRequest?, p1: HttpServletResponse?, p2: AuthenticationException?) {
        println("hellooouu ${p0?.remoteAddr}")

    }
}