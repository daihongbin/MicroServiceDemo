package com.dhb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class ConfigServerApplication {

    // @EnableEurekaClient，用EnableDiscoveryClient就不报错了
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

}
