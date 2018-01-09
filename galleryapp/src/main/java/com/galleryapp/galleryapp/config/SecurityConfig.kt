package com.galleryapp.galleryapp.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@EnableWebSecurity
class SecurityConfig {

    // Authentication : User --> Roles
    @Autowired
    @Throws(Exception::class)
    protected fun configure(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication().withUser("user").password("user")
                .roles("USER").and().withUser("admin").password("admin")
                .roles("USER", "ADMIN")
    }

    // Authorization : Role -> Access

    @Throws(Exception::class)
    protected fun configure(http: HttpSecurity) {
        http.httpBasic().and().authorizeRequests().antMatchers("/anime")
                .hasRole("USER").antMatchers("/anime/**").hasRole("USER").and()
                .csrf().disable().headers().frameOptions().disable()
    }

}
