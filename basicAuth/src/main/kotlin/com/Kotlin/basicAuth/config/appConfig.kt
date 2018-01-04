package com.Kotlin.basicAuth.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.lang.Exception

@Configuration
@EnableSwagger2
@EnableWebSecurity
class appConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private val basicAuthenticationPoint: BasicAuthenticationPoint? = null

    private val apiInfo: ApiInfo
        get() {
            val contact = Contact("Atul Kumar", "www.whatever.com", "atul.kumar@bcstechnology.com")
            return ApiInfoBuilder()
                    .title("Basic Authentication for RESTful API")
                    .description("REST API Training")
                    .version("^2.4.1")
                    .license("Apache 2.0")
                    .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                    .contact(contact)
                    .build()
        }
    @Bean
    open fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.Kotlin.basicAuth.controllers"))
                .paths(PathSelectors.any())
                .build()
    }

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder){
        auth.inMemoryAuthentication().withUser("atul").password("bcstech").roles("USER")
    }

}