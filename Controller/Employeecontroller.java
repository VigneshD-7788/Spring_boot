package com.Springboot_web_rest.Controller;

import com.Springboot_web_rest.Response.Newresponse;
import com.Springboot_web_rest.Service.Employeeservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Employeecontroller {

    private static final Logger logger = LoggerFactory.getLogger(Employeecontroller.class);

    @Autowired
    Employeeservice employeeservice;

    @GetMapping("getPageCount")
    public ResponseEntity getTotalPage(){
        Newresponse res = new Newresponse();
        res.setMessage(employeeservice.getPage());
        return ResponseEntity.ok(res);
    }
    @GetMapping("getTotalCount")
    public ResponseEntity<?> getTotalPages(){
        Newresponse res = new Newresponse();
        res.setMessage("100");
        return ResponseEntity.ok(res);
    }

    @GetMapping("getPage")
    public ResponseEntity searchStudent(@RequestParam(required = false) Integer pageNo){
        Newresponse res= new Newresponse();
        try{
            return ResponseEntity.ok(employeeservice.searchEmployee(pageNo));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(res);
        }
    }
}
