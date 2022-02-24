package com.Springboot_web_rest.Controller;

import com.Springboot_web_rest.Request.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @PostMapping("/users")
    public String printData(@RequestBody User credentials) {
        System.out.println("Printing the user data:" + credentials.getFirstName());
        System.out.println("Printing the user data:" + credentials.getLastName());
        System.out.println("Printing the user data:" + credentials.getAge());
        return "Hi vignesh";
    }

}

