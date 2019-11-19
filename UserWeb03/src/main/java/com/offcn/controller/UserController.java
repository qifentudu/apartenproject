package com.offcn.controller;

import com.offcn.po.User;
import com.offcn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
public class UserController {

    //RestTemplate 是spring提供专门调用rest接口一个模板工具类
    // @Autowired
    //  private RestTemplate restTemplate;

    //Eureka 客户端对象，通过该对象去Euraka去查找对应的服务提供者
  /*  @Autowired
    private DiscoveryClient discoveryClient;*/
 /* @Autowired
  private LoadBalancerClient loadBalancerClient;*/
    @Autowired
    private UserService userService;

    //编写查找方法
    public String findService() {
      /*  List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("USERPROVIDER");
        //获取第一个服务
        ServiceInstance instance = serviceInstanceList.get(0);*/
       /* ServiceInstance instance = loadBalancerClient.choose("USERPROVIDER");
        //获取服务地址
        String host = instance.getHost();
        //获取服务器端口号
        int port = instance.getPort();*/
        /*  return "http://" + host + ":" + port;*/
        return "http://USERPROVIDER/";
    }

    //显示全部用户数据列表
    @RequestMapping("/")
    public String findAll(Model model) {
        //Map map = restTemplate.getForObject(findService() + "user/", Map.class);
        Map<String, Object> map = userService.findAll();
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
        // String str = restTemplate.postForObject(findService() + "user/", user, String.class);
        String str = userService.add(user);
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
        //  ResponseEntity<User> entity = restTemplate.getForEntity(findService() + "user/" + id, User.class);
        //User body = entity.getBody();
        User user = userService.findOne(id);
        model.addAttribute("user",user);
        return "user/userEdit";
    }
    //保存修改的数据
    @RequestMapping("/update")
    public String update(User user){
        //  restTemplate.put(findService()+"user/",user);
        userService.update(user);
        return "redirect:/";
    }
    //删除
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        //restTemplate.delete(findService()+"user/"+id);
        userService.delete(id);
        return "redirect:/";
    }
}
