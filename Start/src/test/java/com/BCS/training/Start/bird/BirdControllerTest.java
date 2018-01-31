package com.BCS.training.Start.bird;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BirdController.class, secure = false)
public class BirdControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BirdController birdController;

    @Test
    public void testbirds() throws Exception{

        Bird mockBird = new Bird();
        mockBird.setId(1L);
        mockBird.setScientificName("Atlantic canary");
        mockBird.setSpecie("serinus canaria");
        mockBird.setMass(11d);
        mockBird.setLength(10);

        String inputJson = this.mapToJson(mockBird);
        String URI = "/birds";
        Mockito.when(birdController.createBird(Mockito.any(Bird.class)))
                .thenReturn(mockBird);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputJson = response.getContentAsString();

        assertThat(outputJson).isEqualTo(inputJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void getBird() throws Exception{
        Bird mockBird = new Bird();
        mockBird.setId(1L);
        mockBird.setScientificName("Atlantic canary");
        mockBird.setSpecie("serinus canaria");
        mockBird.setMass(11d);
        mockBird.setLength(10);

        Mockito.when(birdController.getBird(Mockito.anyLong())).thenReturn(mockBird);

        String URI = "/birds/1";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.mapToJson(mockBird);
        String outputJson = result.getResponse().getContentAsString();
        assertThat(outputJson).isEqualTo(expectedJson);

    }

    private String mapToJson(Object object) throws JsonProcessingException,IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}