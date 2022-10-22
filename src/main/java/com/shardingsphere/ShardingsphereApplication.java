package com.shardingsphere;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;

@SpringBootApplication
public class ShardingsphereApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingsphereApplication.class, args);
    }

}
