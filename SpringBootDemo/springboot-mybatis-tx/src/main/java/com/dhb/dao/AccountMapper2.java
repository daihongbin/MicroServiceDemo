package com.dhb.dao;

import org.apache.ibatis.annotations.Param;

public interface AccountMapper2 {
    int update(@Param("money")double money,@Param("id") int id);
}
