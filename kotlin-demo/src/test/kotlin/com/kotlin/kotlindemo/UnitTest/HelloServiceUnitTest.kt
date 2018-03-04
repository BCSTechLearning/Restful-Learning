package com.kotlin.kotlindemo.UnitTest

import com.kotlin.kotlindemo.HelloService
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HelloServiceUnitTest {

    @InjectMocks
    lateinit var helloService : HelloService

    @Test
    fun testHelloController(){
      val result = helloService.getHello()
        assertNotNull(result)
        assertEquals("Hello service!",result)
    }
}