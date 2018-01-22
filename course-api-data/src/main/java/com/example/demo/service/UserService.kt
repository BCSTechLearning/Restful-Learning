package com.example.demo.service

import com.example.demo.model.User
import com.example.demo.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    private val repo: UserRepository? = null

    val passwordEncoder: PasswordEncoder
        @Bean
        get() = BCryptPasswordEncoder()

    fun save(user: User) {
        user.password = passwordEncoder.encode(user.password)
        repo!!.save(user)
    }

}
