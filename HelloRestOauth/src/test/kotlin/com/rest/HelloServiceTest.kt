package com.rest

import com.rest.REST.Controller.HelloService
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.runners.MockitoJUnitRunner

// > Service
@RunWith(MockitoJUnitRunner::class)
class HelloServiceUnitTest {

    @InjectMocks
    lateinit var helloService: HelloService

    @Test
    fun testHelloController() {
        val result = helloService.getHello()
        Assert.assertNotNull(result)
        Assert.assertEquals("Hello service!", result)
    }
}
