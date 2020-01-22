package com.dhb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringbootCacheDataWithSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCacheDataWithSpringApplication.class, args);
    }

}
