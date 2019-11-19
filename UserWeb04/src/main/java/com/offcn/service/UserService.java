package com.offcn.service;

import com.offcn.config.FeignConfig;
import com.offcn.po.User;
import com.offcn.service.impl.UserServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@FeignClient(value = "userprovider",configuration = FeignConfig.class,fallback = UserServiceImpl.class)
public interface UserService {
    //新增
    @PostMapping("/user/")
    public String add(User user);
    //修改
    @PutMapping("/user/")
    public void update(User user);
    //删除
    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable("id") Long id);
    //查询全部
    @GetMapping("/user/")
     public Map<String,Object> findAll();
     //根据ID查询
    @GetMapping("/user/{id}")
    public User findOne(@PathVariable("id") Long id);

}
