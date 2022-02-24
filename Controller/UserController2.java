package com.Springboot_web_rest.Controller;

import com.Springboot_web_rest.Request.User2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController2 {

    @GetMapping("/multiuser")
    public ResponseEntity<List<User2>> users() {
        List<User2> multiusers = new ArrayList<>();
        multiusers.add(new User2(1, "Ramesh"));
        multiusers.add(new User2(2, "Tony"));
        multiusers.add(new User2(3, "Tom"));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "UserController2");
        System.out.println(headers);
        return ResponseEntity.accepted().headers(headers).body(multiusers);
    }

    @PostMapping("/multiuser1")
    @ResponseBody
    public List<User2> users1() throws Exception {
        List<User2> multiusers1 = new ArrayList<>();
        multiusers1.add(new User2(1, "Ramesh"));
        multiusers1.add(new User2(2, "Tony"));
        multiusers1.add(new User2(3, "Tom"));
        return multiusers1;
    }

        }



