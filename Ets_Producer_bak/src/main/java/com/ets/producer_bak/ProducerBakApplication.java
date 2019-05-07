package com.ets.producer_bak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//提供服务端向Eureka服务端注册
@EnableEurekaClient
public class ProducerBakApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerBakApplication.class, args);
    }

}
