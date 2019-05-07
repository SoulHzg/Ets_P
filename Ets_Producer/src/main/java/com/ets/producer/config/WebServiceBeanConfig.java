package com.ets.producer.config;

import com.ets.producer.webservice.entity.WebServiceClassNameEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebServiceBeanConfig {

    @Bean
    public WebServiceClassNameEntity classNameEnetity(){
        return new WebServiceClassNameEntity();
    }
}
