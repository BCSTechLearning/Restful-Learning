package com.demo.springKotlinDemo.config

import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.PrintWriter
import javax.servlet.http.HttpServletResponse
import javax.servlet.ServletException
import java.io.IOException
import javax.naming.AuthenticationException
import javax.servlet.http.HttpServletRequest


@Component
        public class AuthenticationEntryPoint: BasicAuthenticationEntryPoint(){

    @Override
    @Throws(IOException::class, ServletException::class)
    fun commence(request: HttpServletRequest, response: HttpServletResponse, authEx: AuthenticationException) {
        response.addHeader("WWW-Authenticate", "Basic realm=" + realmName)
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        val writer = response.writer
        writer.println("HTTP Status 401 - " + authEx.localizedMessage)
    }

    @Override
    @Throws(Exception::class)
    public override fun afterPropertiesSet() {
        realmName = "DeveloperStack"
        super.afterPropertiesSet()
    }
}