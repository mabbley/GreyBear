package com.bear.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.bear.*"})
public class StartAdmin {
    public static void main(String[] args) {
        SpringApplication.run(StartAdmin.class, args);
    }
}
