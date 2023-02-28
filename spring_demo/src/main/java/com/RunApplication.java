package com;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootApplication
public class RunApplication {
    public static ApplicationContext application;

    public static void main(String[] args) {
        application = SpringApplication.run(RunApplication.class, args);
    }
}
