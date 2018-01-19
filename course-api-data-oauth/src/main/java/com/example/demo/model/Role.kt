package com.example.demo.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Role {

    @Id
    @GeneratedValue
    private val id: Long? = null
    var name: String = ""

    internal constructor() {}

    constructor(name: String) {
        this.name = name
    }
}
