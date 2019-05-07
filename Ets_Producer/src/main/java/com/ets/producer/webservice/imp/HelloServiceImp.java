package com.ets.producer.webservice.imp;

import com.alibaba.fastjson.JSONObject;
import com.ets.producer.annotation.WebServiceBean;
import com.ets.producer.webservice.HelloService;

//自定义WebServiceBean注解 value不能为空，校验重复
@WebServiceBean("hello")
public class HelloServiceImp implements HelloService {
    @Override
    public String sayHello() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("USER_NAME","胡志刚");
        jsonObject.put("USER_CODE","12345");
        return JSONObject.toJSONString(jsonObject);
    }
}
