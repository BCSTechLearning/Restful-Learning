package com.rest

import com.rest.REST.Controller.Hello
import com.rest.REST.Controller.HelloController
import com.rest.REST.Controller.HelloService
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner

// UNIT TESTS
// > Controller
@RunWith(MockitoJUnitRunner::class)
class HelloControllerUnitTest {

    @InjectMocks
    lateinit var helloController: HelloController
    @Mock
    lateinit var helloService: HelloService
    @Test
    fun testHelloController() {
        val result = helloController.helloString()
        Assert.assertNotNull(result)
        Assert.assertEquals("Hello string!", result)
    }
    @Test
    fun testHelloService() {
        Mockito.doReturn("Hello service!").`when`(helloService).getHello()
        val result = helloController.helloService()
        Assert.assertNotNull(result)
        Assert.assertEquals("Hello service!", result)
    }
    @Test
    fun testHelloDto() {
        val result = helloController.helloData()
        Assert.assertNotNull(result)
        Assert.assertEquals(Hello("Hello data!"), result)
    }
}

