package com.galleryapp.galleryapp.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
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

@Configuration
@EnableSwagger2
@EnableWebSecurity
class ApplicationConfig : WebSecurityConfigurerAdapter() {
    @Autowired
    private val basicAuthenticationPoint: BasicAuthenticationPoint? = null
    private val apiInfo: ApiInfo
        get() {
            val contact = Contact("Chriszer Tamayo", "none", "chriszer.myth@gmail.com")
            return ApiInfoBuilder()
                    .title("Basic Gallery Authentication for REST service ")
                    .description("Gallery App rest practice")
                    .version("1.0.0")
                    .license("Apache 2.0")
                    .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                    .contact(contact)
                    .build()
        }

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.galleryapp.galleryapp.controllers"))
                .paths(PathSelectors.any())
                .build()
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
        http.authorizeRequests().antMatchers("/anime", "/anime/**").permitAll()
                .anyRequest().authenticated().and()
        http.httpBasic().authenticationEntryPoint(basicAuthenticationPoint)

    }

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication().withUser("anime").password("anime").roles("USER")
    }
}