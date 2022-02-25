package com.Springboot_web_rest.Interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectJSample {
    private static final Logger logger = LoggerFactory.getLogger(AspectJSample.class);

    long currentTime;

    @Before(value = "execution(* com.Springboot_web_rest.Service.Studentservice.*(..))")
    public void beforeExecution(JoinPoint Jp){
        currentTime=System.currentTimeMillis();
        logger.info("This is before method before calling service"+":"+currentTime);
    }
    @After(value = "execution(* com.Springboot_web_rest.Service.Studentservice.*(..))")
    public void afterExecution(JoinPoint Jp){
        logger.info("Time taken for execution is "+(System.currentTimeMillis()-currentTime));
        logger.info("This is after calling service");
    }
}
