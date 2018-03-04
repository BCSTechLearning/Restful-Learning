package com.kotlin.kotlindemo.UnitTest

import com.kotlin.kotlindemo.Hello
import com.kotlin.kotlindemo.HelloResource
import com.kotlin.kotlindemo.HelloService
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HelloResourceUnitTest {

    @InjectMocks
    lateinit var helloResource: HelloResource

    @Mock
    lateinit var  helloService: HelloService

    @Test
    fun testHelloController(){
        val result = helloResource.helloString()
        assertNotNull(result)
        assertEquals("Hello string!",result)
    }
    @Test
    fun testHelloService() {
        doReturn("Hello service!").`when`(helloService).getHello()
        val result = helloResource.helloService()
        assertNotNull(result)
        assertEquals("Hello service!", result)
    }
    @Test
    fun testHelloDto() {
        val result = helloResource.helloData()
        assertNotNull(result)
        assertEquals(Hello("Hello data!"), result)
    }

}