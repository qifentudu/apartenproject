package com.offcn.controller;

import com.offcn.po.User;
import com.offcn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //获取全部用户数据
    @GetMapping("/")
    public Map<String,Object> findAall(){
        List<User> list = userService.findAll();
        Map<String, Object> map = new HashMap<>();
        map.put("list",list);
        map.put("version","UserProvider01");//返回一个版本号，区分服务
        return map;
    }
    //获取指定ID用户
    @GetMapping("/{id}")
    public User findOne(@PathVariable("id") Long id){
        return userService.findOne(id);
    }
    //新增用户
    @PostMapping("/")
    public String add(@RequestBody User user){
        userService.add(user);
        return "add-ok";
    }
    //修改用户
    @PutMapping("/")
    public String update (@RequestBody User user){
        userService.update(user);
        return "update-ok";
    }
    //删除用户
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        userService.delete(id);
    }

}