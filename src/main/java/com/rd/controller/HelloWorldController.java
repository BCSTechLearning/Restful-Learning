package com.rd.controller;

import com.rd.error.ResourceNotFoundException;
import com.rd.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RestController
@Component
@RequestMapping("/hello")
public class HelloWorldController {

    @Autowired
    private HelloWorldService helloWorldService;
    @RequestMapping(method = RequestMethod.GET,value = "/{name}")
    public String sayHello(@PathVariable("name") String name, @RequestParam(value = "op", required = false)String op){
        return helloWorldService.HelloService(name,op);
    }

}
