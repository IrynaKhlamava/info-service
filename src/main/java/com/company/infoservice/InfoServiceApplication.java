package com.company.infoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableScheduling
@EnableCaching
@SpringBootApplication
public class InfoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfoServiceApplication.class, args);
    }

}
