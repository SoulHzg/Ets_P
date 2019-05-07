package com.ets.customer.services.imp;

import com.ets.customer.services.UserTestService;
import org.springframework.stereotype.Component;

@Component
public class UserTestServiceimp implements UserTestService {
    @Override
    public String qryUser() {
        return "我是Hystrix...";
    }
}
