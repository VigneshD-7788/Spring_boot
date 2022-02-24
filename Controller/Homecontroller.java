package com.Springboot_web_rest.Controller;

import com.Springboot_web_rest.Model.newmodel;
import com.Springboot_web_rest.Repos.Userrepos;
import com.Springboot_web_rest.Service.Homeservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hi_user")

public class Homecontroller {
    @GetMapping("getuser")
    public ResponseEntity getIntegerId() {
        return ResponseEntity.ok(this.homeservice.getemployeeFromemployeetable());
    }

@Autowired
    Userrepos userrepos;
    @Autowired
    Homeservice homeservice;

    @GetMapping("hello")
    public String getName(@RequestParam String name, @RequestParam String email) {
        System.out.println(email);
        return this.homeservice.getName() + "--get--" + name;
    }

    @GetMapping("hello1")
    public String getName() {
        return " hi hello1";
    }

    @PostMapping("hello")
    public String postName(@RequestParam String name, @RequestParam String email) {
        System.out.println(email);
        return this.homeservice.getName() + "--post--" + name;
    }

    @PostMapping("hello1")
    public String PostName() {
        return " hi hello1";
    }


    @PostMapping("store_user2")
    public ResponseEntity store_user2(@RequestBody newmodel reg) {
        newmodel res = new newmodel();
        try {
            this.homeservice.getemployeebyId(1);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(res);

        }

    }

}