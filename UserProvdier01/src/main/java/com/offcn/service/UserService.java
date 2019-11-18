package com.offcn.service;

import com.offcn.po.User;

import java.util.List;

public interface UserService {
    //新增
    public void add(User user);
    //修改
    public void update(User user);
    //删除
    public void delete(Long id);
    //查询全部
     public List<User> findAll();
     //根据ID查询
    public User findOne(Long id);

}
