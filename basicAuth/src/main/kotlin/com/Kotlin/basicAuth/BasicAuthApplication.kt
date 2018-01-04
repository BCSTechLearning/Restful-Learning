package com.Kotlin.basicAuth

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class BasicAuthApplication

fun main(args: Array<String>) {
    SpringApplication.run(BasicAuthApplication::class.java, *args)
}
