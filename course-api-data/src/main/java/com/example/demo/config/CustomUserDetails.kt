package com.example.demo.config


import com.example.demo.model.Role
import com.example.demo.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

import java.util.ArrayList

/**
 * Provides a basic implementation of the UserDetails interface
 */
class CustomUserDetails(byUsername: User) : UserDetails {


    private val password: String?
    private val username: String?
    private var authorities: Collection<GrantedAuthority>? = null

    init {
        this.username = byUsername.username
        this.password = byUsername.password

        val auths = ArrayList<GrantedAuthority>()
        for (role in byUsername.roles!!) {
            val name = role.name.toUpperCase()
            //Make sure that all roles start with "ROLE_"

            this.authorities = auths
        }
        //return authorities;
    }

    /**
     * Translates the List<Role> to a List<GrantedAuthority>
     * @param roles the input list of roles.
     * @return a list of granted authorities
    </GrantedAuthority></Role> */
    private fun translate(roles: List<Role>): Collection<GrantedAuthority>? {
        return authorities
    }

    override fun getAuthorities(): Collection<GrantedAuthority>? {
        return authorities
    }

    override fun getPassword(): String? {
        return password
    }

    override fun getUsername(): String? {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}
