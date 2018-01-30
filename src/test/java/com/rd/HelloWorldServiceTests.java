package com.rd;

import com.rd.service.HelloWorldService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class HelloWorldServiceTests {

    @InjectMocks
    HelloWorldService helloWorldService;


//    @Test
//    public Void HelloWorldServiceCustomExceptionTest(){
//        String result = helloWorldService.HelloService("Alok",null);
//        assertEquals("Hello Alok",result);
//    }
}
