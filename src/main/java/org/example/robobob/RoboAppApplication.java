package org.example.robobob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example", "org.example.robobob"})
public class RoboAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(RoboAppApplication.class, args);
    }
}