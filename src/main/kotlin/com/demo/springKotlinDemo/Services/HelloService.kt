package com.demo.springKotlinDemo.Services

import com.demo.springKotlinDemo.Models.Hello
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
        class HelloService{
    val Log = LoggerFactory.getLogger(this.javaClass.name)

    fun getHello(): String{
        Log.info("writing first log")
 //       var mes: String = "concatinate"
//        if(true){
//            Log.error("error log printing")
//            throw Exception("exception found!!! "+mes)
//        }
        return "Hello Service"
    }


    fun getHelloData() = Hello("hello data from service",1)
}