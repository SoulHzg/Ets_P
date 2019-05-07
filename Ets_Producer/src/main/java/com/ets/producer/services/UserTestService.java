package com.ets.producer.services;

import com.alibaba.fastjson.JSONObject;
import com.ets.common.bean.UserBean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

//feign通信，服务提供者这里必须要用RestController
@RestController
public class UserTestService implements com.ets.api.services.UserTestService {
    @Override
    public String qryUser() {
        UserBean userBean = new UserBean();
        userBean.setId("2");
        userBean.setName("lhl");
        userBean.setAge("26");
        return JSONObject.toJSONString(userBean);
    }
}
