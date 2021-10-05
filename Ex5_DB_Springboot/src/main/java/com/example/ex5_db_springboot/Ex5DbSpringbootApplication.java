package com.example.ex5_db_springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ex5DbSpringbootApplication {

    
    @Autowired
    public Ex5DbSpringbootApplication() {
        // TODO document why this constructor is empty
    }

    public static void main(String[] args) {
        SpringApplication.run(Ex5DbSpringbootApplication.class, args);
    }


}
