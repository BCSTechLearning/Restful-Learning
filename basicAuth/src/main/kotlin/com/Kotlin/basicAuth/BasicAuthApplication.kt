package com.Kotlin.basicAuth

import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.util.logging.Logger

@SpringBootApplication
class BasicAuthApplication



fun main(args: Array<String>) {
    SpringApplication.run(BasicAuthApplication::class.java, *args)
}
