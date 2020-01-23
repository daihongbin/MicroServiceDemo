package com.dhb;

import com.dhb.service.Service;
import com.dhb.service.ServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Import(ServiceConfiguration.class)
@RestController
public class Application {

    private final Service service;

    @Autowired
    public Application(Service service){
        this.service = service;
    }

    @GetMapping("/")
    public String home(){
        return service.message();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
