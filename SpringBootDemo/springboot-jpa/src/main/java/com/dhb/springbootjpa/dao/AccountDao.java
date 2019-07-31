package com.dhb.springbootjpa.dao;

import com.dhb.springbootjpa.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDao extends JpaRepository<Account,Integer> {

}
