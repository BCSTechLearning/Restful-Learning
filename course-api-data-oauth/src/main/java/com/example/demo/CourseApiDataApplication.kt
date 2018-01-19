package com.example.demo

import com.example.demo.config.CustomUserDetails
import com.example.demo.model.Role
import com.example.demo.model.User
import com.example.demo.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder

import java.util.Arrays

@EnableAutoConfiguration
@ComponentScan
class CourseApiDataApplication {

    @Autowired
    private val passwordEncoder: PasswordEncoder? = null


    @Autowired
    @Throws(Exception::class)
    fun authenticationManager(builder: AuthenticationManagerBuilder, repo: UserRepository) {
        //Setup a default user if db is empty


        if (repo.count() == 0L)
            repo.save(User("user", "user", Arrays.asList(Role("USER"), Role("ACTUATOR"))))
        builder.userDetailsService(UserDetailsService { s -> CustomUserDetails(repo.findByUsername(s)) })

    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(CourseApiDataApplication::class.java, *args)
        }
    }
}
