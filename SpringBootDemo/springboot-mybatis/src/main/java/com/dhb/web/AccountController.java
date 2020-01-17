package com.dhb.web;

import com.dhb.entity.Account;
import com.dhb.service.AccountService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Account> getAccounts(){
        return accountService.findAccountList();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Account getAccountById(@PathVariable("id")int id){
        return accountService.findAccount(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public String updateAccount(@PathVariable("id")int id, @RequestParam(value = "name",required = true)String name,
        @RequestParam(value = "money",required = true) double money){
        int t = accountService.update(name,money,id);
        if(t == 1){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id")int id){
        int t = accountService.delete(id);
        if(t == 1){
            return "success";
        }else{
            return "fail";
        }
    }

    @ApiOperation(value = "创建账户",notes = "传入参数创建账户")
    @ApiImplicitParam(name="name",value = "姓名",paramType = "query",required = true,dataType = "string")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public String postAccount(@RequestParam(value = "name")String name,@RequestParam(value = "money")double money){
        int t = accountService.add(name,money);
        if(t == 1){
            return "success";
        }else{
            return "fail";
        }
    }
}
