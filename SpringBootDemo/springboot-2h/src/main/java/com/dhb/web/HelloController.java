package com.dhb.web;

import com.dhb.entity.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController // 等同于同时加上了@Controller和@ResponseBody
public class HelloController {

//    @Value("${girl.name}")
//    private String name;
//
//    @Value("${girl.age}")
//    private Integer age;
//
//    @Value("${girl.content}")
//    private String content;

    @Autowired
    private GirlProperties girlProperties;

    @RequestMapping(value = {"/hello","/hi"},method = RequestMethod.GET)
    public String say(){
        // return "hi you!!!" + name + age + content;
        return "hi you!!!My name is " + girlProperties.getName();
    }

}
