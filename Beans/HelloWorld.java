package com.Springboot_web_rest.Beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);
    private String message;

    public String getMessage() {
        logger.info("Your message:"+message);
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
