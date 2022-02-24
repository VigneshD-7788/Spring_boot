package com.Springboot_web_rest.Interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class ApiroleAcess {
    private static final Logger logger = LoggerFactory.getLogger(ApiroleAcess.class);

    public static boolean checkRole(String role,String api){
        logger.info(api);
        try{
            logger.info("START");
            HashMap<String,String> apiList = new HashMap<String,String>();
            apiList.put("getStudent","student");
            apiList.put("getStudents","admin");
            String url[]= api.split("/");
            logger.info(""+role);
            logger.info("hashmap value :"+url[3]);
            return apiList.get(url[3]).equals(role);

        }catch(Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }
}
