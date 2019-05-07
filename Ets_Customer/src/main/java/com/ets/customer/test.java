package com.ets.customer;

import com.ets.common.until.StringUntil;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Component
@RequestMapping("/test")
public class test {
    @RequestMapping("/getDateFormat")
    @ResponseBody
    public String getDateFormat(){
        return StringUntil.defaultFormat.format(new Date());
    }
}
