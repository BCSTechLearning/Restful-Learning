package com.kotlin.kotlindemo

import org.springframework.stereotype.Service

@Service
class HelloService {
    fun getHello() = "Hello service!"
}