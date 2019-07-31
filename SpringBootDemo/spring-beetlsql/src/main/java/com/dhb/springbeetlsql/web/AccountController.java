package com.dhb.springbeetlsql.web;

import com.dhb.springbeetlsql.dao.AccountDao;
import com.dhb.springbeetlsql.entity.Account;
import org.beetl.sql.core.db.KeyHolder;
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
        return accountDao.all();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Account getAccountById(@PathVariable("id")int id){
        return accountDao.unique(id);
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public Account getAccountById(@RequestParam("name") String name){
        return accountDao.selectAccountByName(name);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public String updateAccount(@PathVariable("id")int id,@RequestParam(value = "name",required = true)String name,@RequestParam(value = "money",required = true)double money){
        Account account = new Account();
        account.setMoney(money);
        account.setName(name);
        account.setId(id);

        int t = accountDao.updateById(account);
        if(t == 1){
            return account.toString();
        }else{
            return "fail";
        }
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public String postAccount(@RequestParam(value = "name")String name,@RequestParam(value = "money")double money){
        Account account = new Account();
        account.setMoney(money);
        account.setName(name);

        KeyHolder t = accountDao.insertReturnKey(account);
        if(t.getInt() > 0){
            return account.toString();
        }else{
            return "fail";
        }
    }

}
