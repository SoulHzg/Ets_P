package com.ets.producer.controller;

import com.ets.common.bean.UserBean;
import com.ets.producer.rabbitUtil.RabbitSender;
import com.ets.producer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private RabbitSender rabbitSender;

    @RequestMapping("/getUser")
    @ResponseBody
    public UserBean getUser(){
        UserBean  userBean = new UserBean();
        userBean.setId("1");
        userBean.setName("hzg");
        userBean.setAge("27");
        return userBean;
    }

    @RequestMapping("/rabbitSend")
    public void rabbitSend(){
        rabbitSender.sendDirectQueue();
    }


}
