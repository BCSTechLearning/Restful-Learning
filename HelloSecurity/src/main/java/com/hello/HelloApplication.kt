package com.hello

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@EnableAutoConfiguration
@ComponentScan
class HelloApplication {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            SpringApplication.run(HelloApplication::class.java, *args)
        }
    }

    }

