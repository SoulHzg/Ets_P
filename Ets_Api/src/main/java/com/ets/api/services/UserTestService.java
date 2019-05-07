package com.ets.api.services;


import org.springframework.web.bind.annotation.*;

public interface UserTestService {
    //feign通信，接口这里必须使用RequestMapping，不能使用GetMapping，而且必须添加method属性
    @RequestMapping(value = "/qryUser",method = RequestMethod.GET)
    @ResponseBody
    public String qryUser();
}
