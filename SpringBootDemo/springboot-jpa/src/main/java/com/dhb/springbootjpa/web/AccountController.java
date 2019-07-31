package com.dhb.springbootjpa.web;

import com.dhb.springbootjpa.dao.AccountDao;
import com.dhb.springbootjpa.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountDao accountDao;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Account> getAccounts(){
        return accountDao.findAll();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public String updateAccount(@PathVariable("id") int id, @RequestParam(value = "name",required = true)String name,@RequestParam(value = "money",required = true)double money){
        Account account = new Account();
        account.setMoney(money);
        account.setName(name);

        Account accountToSave = accountDao.save(account);
        return accountToSave.toString();
    }
}
