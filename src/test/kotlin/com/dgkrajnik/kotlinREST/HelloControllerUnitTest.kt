package com.dgkrajnik.kotlinREST

import com.dgkrajnik.kotlinREST.REST.Errors.EntityNotFoundException
import com.dgkrajnik.kotlinREST.REST.Errors.ValidationFailedException
import com.dgkrajnik.kotlinREST.REST.SpringHelloController
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.runners.MockitoJUnitRunner
import org.springframework.http.converter.HttpMessageNotReadableException

/**
 * Unit tests for the controller's endpoints.
 */
@RunWith(MockitoJUnitRunner::class)
class HelloControllerUnitTest {
    @InjectMocks
    lateinit var helloController: SpringHelloController

    @Mock
    lateinit var helloService: HelloService

    @Test
    fun testSpringHelloController() {
        val result = helloController.helloString()
        assertNotNull(result)
        assertEquals(result, "Hello, Spring!")
    }

    @Test
    fun testSpringHelloService() {
        doReturn("Hello, Service!").`when`(helloService).helloAsAService()
        val result = helloController.helloService()
        assertNotNull(result)
        assertEquals("Hello, Service!", result)
    }

    @Test
    fun testSpringHelloDTO() {
        val result = helloController.helloData()
        assertNotNull(result)
        assertEquals(HelloData("Hello, Data!"), result)
    }

    @Test
    fun testSpringOAuthData() {
        val result = helloController.helloOAuthData()
        assertNotNull(result)
        assertEquals(HelloData("Hello, OAuth!"), result)
    }

    @Test
    fun testSpringError() {
        try {
            helloController.badBoy()
        } catch(ex: HttpMessageNotReadableException) {
            assertEquals("Wink wonk", ex.message)
            return
        }
        fail()
    }

    @Test
    fun testSpringBadGet() {
        var request = helloController.badReq(22)
        assertNotNull(request)
        assertEquals("Good Stuff", request.jsonshim)
        try {
            helloController.badReq(21)
        } catch(ex: EntityNotFoundException) {
            assertEquals("Entity 21 != 22 not found.", ex.message)
            return
        }
        fail()
    }

    @Test
    fun testSpringBadPost() {
        var result = helloController.badPost(22)
        assertNotNull(result)
        assertEquals("I got 22", result.jsonshim)
        try {
            helloController.badPost(23)
        } catch(ex: ValidationFailedException) {
            assertEquals("reqparam", ex.field)
            assertEquals(23, ex.rejectedValue)
            return
        }
        fail()
    }
}
