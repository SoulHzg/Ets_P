package com.ets.producer.services;

import com.alibaba.fastjson.JSONObject;
import com.ets.common.bean.UserBean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequestMapping("/userService")
public class UserService {
    @RequestMapping(value = "/qryUser")
    @ResponseBody
    public String qryUser() {
        UserBean userBean = new UserBean();
        userBean.setId("1");
        userBean.setName("hzg");
        userBean.setAge("27");
        return JSONObject.toJSONString(userBean);
    }
}
