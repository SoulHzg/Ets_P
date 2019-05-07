package com.ets.customer.services;

import com.ets.customer.services.imp.UserTestServiceimp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//Producer为服务提供者的服务名称
@FeignClient(value = "http://Producer",fallback = UserTestServiceimp.class)
public interface UserTestService extends com.ets.api.services.UserTestService {
    /**
     * 说明：因为提供服务项有服务名为producer，则需要重写方法，请求地址指向/producer/qryUser。
     *      还可以不设置服务提供者的名称。
     *
     * */
    @Override
    @RequestMapping(value = "/producer/qryUser",method = RequestMethod.GET)
    @ResponseBody
    public String qryUser();
}
