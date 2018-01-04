package com.Kotlin.basicAuth.controllers

import com.Kotlin.basicAuth.beans.data
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
@Api(value = "api", description = "Rest Api for the user login", tags = arrayOf("User API"))
class userControl{

    var Log = LoggerFactory.getLogger(this.javaClass.name)

    @GetMapping(value = arrayOf("/user/{name}"), produces = arrayOf("application/json"))
    @ApiOperation(value = "Display Welcome message to the Uer", response = data::class)
    @ApiResponses( value = arrayOf(
            ApiResponse(code = 200, message = "OK"),
            ApiResponse(code = 401, message = "You are not authorized to access this resource"),
            ApiResponse(code = 404, message = "Resource not found")
    ))
    fun message(@PathVariable name: String): data {
        Log.info("User Logged in")
        return data(name,  " Hello Admin " + name)
    }

}