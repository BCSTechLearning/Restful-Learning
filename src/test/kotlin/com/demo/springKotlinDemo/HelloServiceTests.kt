package com.demo.springKotlinDemo

import com.demo.springKotlinDemo.Controllers.HelloController
import com.demo.springKotlinDemo.Services.HelloService
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.runners.MockitoJUnitRunner
import org.springframework.boot.test.context.SpringBootTest

@RunWith(MockitoJUnitRunner::class)
        @SpringBootTest
        class HelloServiceTests{

    @InjectMocks
    lateinit var helloController : HelloController

    @Mock
    lateinit var helloService : HelloService

    @Test
    fun testHelloService(){
        val result = helloService.getHello()
        println("hello running tests")
        assertNotNull(result)
        assertEquals("Hello Service", result)
    }

    @Test
    fun testHelloController(){
        doReturn("Hello Service").`when`(helloService).getHello()
        val result = helloController.helloService()
        assertNotNull(result)
        assertEquals("Hello Service",result)
    }
}