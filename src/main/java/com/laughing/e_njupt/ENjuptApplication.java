package com.laughing.e_njupt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.laughing.e_njupt.mapper")
@SpringBootApplication
@EnableScheduling
public class ENjuptApplication {

    public static void main(String[] args) {
        SpringApplication.run(ENjuptApplication.class, args);
    }

}
