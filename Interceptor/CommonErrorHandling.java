package com.Springboot_web_rest.Interceptor;

import com.Springboot_web_rest.Response.Loginresponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonErrorHandling {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRunTimeException(RuntimeException e){
        Loginresponse res=new Loginresponse();
        res.setMessage(e.getMessage());
        return ResponseEntity.badRequest().body(res);
    }
}
