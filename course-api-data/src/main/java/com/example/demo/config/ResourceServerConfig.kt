package com.example.demo.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter

/**
 * The @EnableResourceServer annotation adds a filter of type OAuth2AuthenticationProcessingFilter automatically
 * to the Spring Security filter chain.
 */
@Configuration
@EnableResourceServer
class ResourceServerConfig : ResourceServerConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.headers().frameOptions().disable().and()
                .authorizeRequests()
                .antMatchers("/", "/hello").permitAll()
                .antMatchers("/topics/**").authenticated()
    }


}
