package com.example.demo.model

import javax.persistence.*

@Entity
class User {

    @Id
    @GeneratedValue
    private val id: Long? = null
    var username: String? = null
    var password: String? = null
    @OneToMany(fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.ALL))
    var roles: List<Role>? = null

    internal constructor() {}

    constructor(username: String, password: String, roles: List<Role>) {
        this.username = username
        this.password = password
        this.roles = roles
    }
}
