package com.demo.springKotlinDemo

import com.demo.springKotlinDemo.Controllers.HelloController
import com.demo.springKotlinDemo.Services.HelloService
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
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
        assertNotNull(result)
        assertEquals("Hello Service", result)
    }
}