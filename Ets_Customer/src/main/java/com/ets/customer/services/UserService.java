package com.ets.customer.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

//@FeignClient("Producer")
public interface UserService {
    @RequestMapping(value = "/producer/userService/qryUser")
    public String qryUser();
}
