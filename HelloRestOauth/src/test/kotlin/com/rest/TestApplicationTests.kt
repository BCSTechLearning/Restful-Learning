package com.rest

import com.rest.REST.Controller.Hello
import com.rest.REST.Controller.HelloController
import com.rest.REST.Controller.HelloService
import org.hamcrest.CoreMatchers.containsString
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.runners.MockitoJUnitRunner
import org.springframework.boot.json.JacksonJsonParser
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import javax.inject.Inject
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

val client_id = "trusted-client"
val client_secret = "secret"

// INTEGRATION TESTS
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class HelloEndpointIntegrationTest {
    @Inject
    lateinit var testRestTemplate: TestRestTemplate

    @Inject
    lateinit var mockmvc: MockMvc

    @Test
    fun testNonSecured() {
        mockmvc.perform(get("/hello/unsecured"))
                .andExpect(status().isOk)
                .andExpect(content().string(containsString("Beep boop")))
    }

    @Test
    fun noToken() {
        mockmvc.perform(get("/hello/string"))
                .andExpect(status().isUnauthorized)
    }

    @Test
    fun testAccessString() {
        val accessToken = obtainAccessToken("admin", "admin")
        mockmvc.perform(get("/hello/string")
                .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk)
                .andExpect(content().string(containsString("Hello string!")))
    }

    @Test
    fun testNoAccess() {
        // role is USER, so doesn't have access
        val accessToken = obtainAccessToken("user", "pass")
        mockmvc.perform(get("/hello/string")
                .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isForbidden)
    }

    @Test
    fun testHelloService() {
        // resource protected to USER role
        val accessToken = obtainAccessToken("user", "pass")
        mockmvc.perform(get("/hello/service")
                .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk)
                .andExpect(content().string(containsString("Hello service!")))
    }

    @Test
    fun testHelloData() {
        val accessToken = obtainAccessToken("admin", "admin")
        mockmvc.perform(get("/hello/data")
                .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk)
                .andExpect(content().string(containsString("Hello data!")))
    }

    @Test
    fun test404Response() {
        mockmvc.perform(get("/hotdog/cookie"))
                .andExpect(status().isNotFound)
                .andExpect(content().string(containsString("404")))
    }

    /**
     * Obtains an access token from the auth server and returns it as a string.
     */
    fun obtainAccessToken(username: String, password: String): String {
        var params: MultiValueMap<String, String> = LinkedMultiValueMap()
        params.add("grant_type", "password")
        params.add("client_id", client_id)
        params.add("username", username)
        params.add("password", password)

        var result: ResultActions =
                mockmvc.perform(post("/oauth/token")
                        .params(params)
                        .with(httpBasic(client_id, client_secret))
                        .accept("application/json;charset=UTF-8"))
                        .andExpect(status().isOk)
                        .andExpect(content().contentType("application/json;charset=UTF-8"))
        var resultString = result.andReturn().response.contentAsString

        var jsonParser: JacksonJsonParser = JacksonJsonParser()
        return jsonParser.parseMap(resultString).get("access_token").toString()
    }
}



