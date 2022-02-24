package com.Springboot_web_rest.Interceptor;

import com.Springboot_web_rest.Response.Loginresponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CommonErrorHandling {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRunTimeException(RuntimeException e){
        Loginresponse res=new Loginresponse();
        res.setMessage(e.getMessage());
        return ResponseEntity.badRequest().body(res);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions (MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        }
        );
        return ResponseEntity.badRequest().body(errors);
    }
}
