package com.dhb.dao;

import com.dhb.entity.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GirlRep extends JpaRepository<Girl,Integer> {

    public List<Girl> findByAge(Integer age);
}
