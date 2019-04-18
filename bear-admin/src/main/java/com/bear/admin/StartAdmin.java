package com.bear.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.bear.*"})
@MapperScan("com.bear.admin.*.mapper")
public class StartAdmin {
    public static void main(String[] args) {
        SpringApplication.run(StartAdmin.class, args);
    }
}
