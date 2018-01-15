package com.demo.springKotlinDemo.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.web.bind.annotation.ExceptionHandler


@Configuration
        @EnableWebSecurity
        public class SpringSecurityConfig: WebSecurityConfigurerAdapter(){

        @Autowired
        private val authEntryPoint: AuthenticationEntryPoint? = null

        @Override
        @ExceptionHandler(Exception::class)
        protected override fun configure(http: HttpSecurity) {
                http.csrf().disable().authorizeRequests()
                        .anyRequest().authenticated()
                        .and().httpBasic()
                        .authenticationEntryPoint(authEntryPoint)
        }

        @Autowired
        @Throws(Exception::class)
        fun configureGlobal(auth: AuthenticationManagerBuilder) {
                auth.inMemoryAuthentication().withUser("john123").password("password").roles("USER")
        }
}
