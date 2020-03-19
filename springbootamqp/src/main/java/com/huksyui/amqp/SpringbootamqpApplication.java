package com.huksyui.amqp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootamqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootamqpApplication.class, args);
    }

}
