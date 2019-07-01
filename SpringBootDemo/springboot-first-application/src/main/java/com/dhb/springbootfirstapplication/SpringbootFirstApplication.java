package com.dhb.springbootfirstapplication;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class SpringbootFirstApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootFirstApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx){
        return args -> {
            System.out.println("让我们看看SpringBoot为我们注入了哪些bean：");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);

            for(String beanName : beanNames){
                System.out.println(beanName);
            }
        };
    }

}
