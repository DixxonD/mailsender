package ch.tvlla.mailsender.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import javax.sql.DataSource


@Configuration
@EnableWebSecurity
class SecurityConfigAdapter() : WebSecurityConfigurerAdapter() {


    override fun  configure(http: HttpSecurity){
        http
            .csrf().disable()
            .headers().frameOptions().disable()
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

}