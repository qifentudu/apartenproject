package com.offcn.controller;

import com.offcn.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    //RestTemplate 是spring提供专门调用rest接口一个模板工具类
    @Autowired
    private RestTemplate restTemplate;

    //Eureka 客户端对象，通过该对象去Euraka去查找对应的服务提供者
  /*  @Autowired
    private DiscoveryClient discoveryClient;*/
  @Autowired
  private LoadBalancerClient loadBalancerClient;

    //编写查找方法
    public String findService() {
       /* List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("USERPROVIDER");
        //获取第一个服务
        ServiceInstance instance = serviceInstanceList.get(0);
        //获取服务地址
        String host = instance.getHost();
        //获取服务器端口号
        int port = instance.getPort();
        return "http://" + host + ":" + port;*/
       //自带一个负载均衡算法，从现有服务列表，选择一个
        ServiceInstance instance = loadBalancerClient.choose("UserProvider");
        String host = instance.getHost();
        int port = instance.getPort();
        return "http://" + host + ":" + port;
    }

    //显示全部用户数据列表
    @RequestMapping("/")
    public String findAll(Model model) {
        Map map = restTemplate.getForObject(findService() + "user/", Map.class);
        model.addAttribute("list", map.get("list"));
        model.addAttribute("version", map.get("version"));
        return "user/list";
    }

    @RequestMapping("/toAdd")
    //跳转到新增用户界面
    public String toAdd() {
        return "user/userAdd";
    }
    //保存新增用户数据
    @RequestMapping("/add")
    public String add(User user){
        String str = restTemplate.postForObject(findService() + "user/", user, String.class);
        if (str!=null){
            if (str.equals("add-ok")){
                return "redirect:/";
            }
        }
        return "redirect:/";
    }
    //跳转到编辑页面
    @RequestMapping("/toEdit/{id}")
    public String toEdit(@PathVariable("id") Long id, Model model){
        ResponseEntity<User> entity = restTemplate.getForEntity(findService() + "user/" + id, User.class);
        User body = entity.getBody();
        model.addAttribute("user",body);
        return "user/userEdit";
    }
    //保存修改的数据
    @RequestMapping("/update")
    public String update(User user){
        restTemplate.put(findService()+"user/",user);
        return "redirect:/";
    }
    //删除
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        restTemplate.delete(findService()+"user/"+id);
        return "redirect:/";
    }
}
