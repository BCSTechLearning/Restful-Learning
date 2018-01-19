package com.example.demo.repository


import com.example.demo.model.User
import org.springframework.data.jpa.repository.JpaRepository

/**
 * User repository for CRUD operations.
 */
interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User


}
