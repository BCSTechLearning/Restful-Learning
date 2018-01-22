package com.example.demo.controller

import org.springframework.boot.autoconfigure.web.ErrorController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IndexController : ErrorController {
    override fun getErrorPath(): String {
        return "/error"
    }

    @RequestMapping("/error")
    fun error(): String {
        return "No Mapping available"
    }
}
