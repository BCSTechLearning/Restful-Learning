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
    @InjectMocks
    lateinit var demo : HelloService


    @Test
    fun testHelloController(){
        doReturn("Hello Service").`when`(helloService).getHello()
        val result = helloController.helloService()
        assertNotNull(result)
        assertEquals("Hello Service",result)
    }

    @Test
    fun testHelloService(){
        val result = demo.getHello()
        assertNotNull(result)
        assertEquals("Hello Service", result)
    }

    @Test
    fun tesGetHelloData(){
        val result = demo.getHelloData()
        assertNotNull(result.message)
        assertEquals(1,result.id)
    }


}