package com.bear.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class StartConfig {
    public static void main(String[] args) {
        SpringApplication.run(StartConfig.class, args);
    }
}
