package com.Springboot_web_rest.Controller;

import com.Springboot_web_rest.Request.typeclass;
import com.Springboot_web_rest.Response.Newresponse;
import com.Springboot_web_rest.Service.Operatorservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Operatorcontroller {
    @Autowired
    Operatorservice operatorservice;
@PostMapping("calculate")
    public ResponseEntity Calculation(@RequestBody typeclass cl){

    Newresponse rep=new Newresponse();
    try
    {
        this.operatorservice.check(cl);
        rep.setMessage("Successfully Calculation performed");
        return ResponseEntity.ok(rep);

    }
    catch (Exception e)
    {
        e.printStackTrace();
        rep.setMessage(e.getMessage());
        return ResponseEntity.badRequest().body(rep);
    }


}
}

