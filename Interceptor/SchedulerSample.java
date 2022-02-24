package com.Springboot_web_rest.Interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class SchedulerSample {
    private static final Logger logger = LoggerFactory.getLogger(SchedulerSample.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

//    @Scheduled(fixedRate = 5000)
//    public void reportCurrentTime(){
//        logger.info("The time is now {}", dateFormat.format(new Date()));
//    }

    @Scheduled(cron ="*/30 * * * * *")
    public void sampleCurrentTime(){

        logger.info("The time is now {}",dateFormat.format(new Date()));
    }

}
