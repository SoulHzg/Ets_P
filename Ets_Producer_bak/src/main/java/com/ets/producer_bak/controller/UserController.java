package com.ets.producer_bak.controller;

import com.ets.common.bean.UserBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/getUser")
    @ResponseBody
    public UserBean getUser(){
        UserBean  userBean = new UserBean();
        userBean.setId("2");
        userBean.setName("lhl");
        userBean.setAge("26");
        return userBean;
    }
}
