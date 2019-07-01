package com.dhb.springbootfirstapplication.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index(){
        return "来自Spring Boot的问候!";
    }
}
