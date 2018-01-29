package com.rd.service;

import com.rd.error.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {
    private static Logger LOG = LoggerFactory.getLogger(HelloWorldService.class);

    public String HelloService(String name,String op){
        LOG.info("Value for name : "+name+" and value for op : "+op);
        String check = "error";
        if(op==null){
            LOG.info("Name Not provided");
            throw new ResourceNotFoundException(null,400,"No parameter found");
        }
        return "Hello "+name;
    }

}
