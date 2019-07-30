package com.dwelling.sample.authsample

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal


@Configuration
class OktaOAuth2WebSecurityConfigurerAdapter : WebSecurityConfigurerAdapter() {
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .oauth2ResourceServer().jwt();
    }
}


@RestController
internal class MessagesRestController {

    @GetMapping("/messages/user")
    fun getMessages(principal: Principal): List<String> {
        return listOf("user1", "user2")
    }


    @GetMapping("/hello-oauth")
    fun sayHello(principal: Principal): String {
        return "Hello, " + principal.name
    }

    @GetMapping("/")
    fun securedPage(model: Model, principal: Principal): String {
        return "hello "+ principal.name
    }
}



