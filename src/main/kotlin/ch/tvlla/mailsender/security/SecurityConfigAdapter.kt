package ch.tvlla.mailsender.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Configuration
@EnableWebSecurity
class SecurityConfigAdapter() : WebSecurityConfigurerAdapter() {


    override fun  configure(http: HttpSecurity){
        http
            .csrf().disable()
            .headers().frameOptions().disable()

    }

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/styles/**")
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

}