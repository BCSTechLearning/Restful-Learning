package com.example.demo.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Topic {

    @Id
    var id: String? = null
    var name: String? = null
    var description: String? = null

    constructor() {

    }

    constructor(id: String, name: String, description: String) : super() {
        this.id = id
        this.name = name
        this.description = description
    }


}
