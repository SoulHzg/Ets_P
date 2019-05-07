package com.ets.customer.controller;

import com.ets.common.bean.UserBean;
import com.ets.customer.services.UserService;
import com.ets.customer.services.UserTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    /*@Autowired
    private UserService userService;*/

    @Autowired
    private UserTestService userTestService;

    @RequestMapping("/getUser")
    public String getUser(){
        return "redirect:/user/toUser";
    }

    @RequestMapping("/toUser")
    @ResponseBody
    public UserBean toUser(){
        //return restTemplate.getForObject("http://127.0.0.1:8080/producer/user/getUser",UserBean.class);
        return restTemplate.getForObject("http://Producer/producer/user/getUser",UserBean.class);
    }

    /*@RequestMapping(value = "/qryUser")
    @ResponseBody
    public String qryUser(){
        return userService.qryUser();
    }*/

    @RequestMapping(value = "/qryUserTest")
    @ResponseBody
    public String qryUserTest(){
        return userTestService.qryUser();
    }
}
