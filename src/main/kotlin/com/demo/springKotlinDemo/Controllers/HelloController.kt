package com.demo.springKotlinDemo.Controllers

import com.demo.springKotlinDemo.Models.Greeting
import com.demo.springKotlinDemo.Models.Hello
import com.demo.springKotlinDemo.Services.HelloService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong
import javax.validation.constraints.Null
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.security.core.context.SecurityContextHolder
import javax.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.RequestMethod
import javax.servlet.http.HttpServletRequest


@RestController
@RequestMapping("v1")
        class HelloController{
            @GetMapping("/string")
        fun helloString() = "Hello"
    @Autowired
    lateinit var helloService: HelloService

    @GetMapping("/service")
    fun helloService(): String {
        var resp: String = "hello"
        try {
            resp = helloService.getHello()
        }catch (e : Exception){
            resp = e.localizedMessage
        }
        return resp
    }

    @GetMapping("/data")
    fun helloData() = helloService.getHelloData()


    val counter = AtomicLong()
    @GetMapping("/greeting")
    fun greetings(@RequestParam(value = "name", defaultValue = "world")name: String) =
            Greeting(counter.incrementAndGet(),"Hello, $name")


    //=============================================================================
//    @RequestMapping(value = "/logout", method = arrayOf(RequestMethod.GET))
//    fun logoutPage(request: HttpServletRequest, response: HttpServletResponse): String {
//        val auth = SecurityContextHolder.getContext().authentication
//        if (auth != null) {
//            SecurityContextLogoutHandler().logout(request, response, auth)
//        }
//        return "redirect:/login"
//    }

//    @RequestMapping(path="/user", method = RequestMethod.GET)
//    fun userData(request: HttpServletRequest, response: HttpServletResponse): String{
//        return "return data"
//    }
}