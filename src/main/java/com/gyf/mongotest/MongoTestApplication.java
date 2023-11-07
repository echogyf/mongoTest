package com.gyf.mongotest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MongoTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoTestApplication.class, args);
    }

}
