package com.example.demo9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Demo9Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo9Application.class, args);
    }

}
