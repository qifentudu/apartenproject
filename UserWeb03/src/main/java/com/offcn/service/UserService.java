package com.offcn.service;


import com.offcn.po.User;

import org.springframework.web.bind.annotation.*;

import java.util.Map;


public interface UserService {
    //新增

    public String add(User user);
    //修改

    public void update(User user);
    //删除

    public void delete(@PathVariable("id") Long id);
    //查询全部

     public Map<String,Object> findAll();
     //根据ID查询

    public User findOne(@PathVariable("id") Long id);

}
