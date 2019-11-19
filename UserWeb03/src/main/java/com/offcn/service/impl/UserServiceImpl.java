package com.offcn.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.offcn.po.User;
import com.offcn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private RestTemplate restTemplate;
  public String getServerUrl(){
      return "http://USERPROVIDER/";
  }
    @Override
    public String add(User user) {
        String s = restTemplate.postForObject(getServerUrl() + "user/", user, String.class);
        return s;
    }

    @Override
    public void update(User user) {
       restTemplate.put(getServerUrl()+"user/",user);
    }

    @Override
    public void delete(Long id) {
            restTemplate.delete(getServerUrl()+"user/"+id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "findAllbackMethod")
    public Map<String,Object> findAll() {
        ResponseEntity<Map> entity = restTemplate.getForEntity(getServerUrl() + "user/", Map.class);
        Map map = entity.getBody();
       return map;
    }
    public Map<String,Object> findAllbackMethod() {
        System.out.println("熔断被触发");
        Map<String, Object> map = new HashMap<>();
        map.put("list",new ArrayList<>());
        map.put("version","服务调用超时，熔断被触发");
        return map;
  }
    @Override
    public User findOne(Long id) {
        ResponseEntity<User> entity = restTemplate.getForEntity(getServerUrl() + "user/" + id, User.class);
        User user = entity.getBody();
        return user;
    }
}
