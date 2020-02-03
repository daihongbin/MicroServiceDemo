package com.dhb.web;

import com.dhb.dao.GirlRep;
import com.dhb.entity.Girl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GirlController {

    @Autowired
    private GirlRep girlRep;

    /**
     * 查询所有女生列表
     * @return
     */
    @RequestMapping(value = "/girls",method = RequestMethod.GET)
    public List<Girl> getGirlList(){
        return girlRep.findAll();
    }

    @RequestMapping(value = "/girls",method = RequestMethod.POST)
    public Girl addGirl(@RequestParam("name")String name,@RequestParam("age") Integer age,
                        @RequestParam("content")String content){
        Girl girl = new Girl();
        girl.setAge(age);
        girl.setName(name);
        girl.setContent(content);
        return girlRep.save(girl);
    }
}
