package com.Springboot_web_rest.Controller;

import com.Springboot_web_rest.Beans.HelloWorldConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeanController {
    private static final Logger logger = LoggerFactory.getLogger(BeanController.class);

    @Autowired
    HelloWorldConfig config;

    @GetMapping("setBean")
    public String setBean(){
        config.helloWorld().setMessage("hi");
        return "Bean value set";
    }
    @GetMapping("getBean")
    public String getBean(){
        String val = config.helloWorld().getMessage();
        logger.info("Bean value is"+":"+val);
        return "From Bean"+":"+val;
    }

}
