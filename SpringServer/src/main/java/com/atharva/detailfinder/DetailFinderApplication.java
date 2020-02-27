package com.atharva.detailfinder;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(value = "classpath*:applicationContext.xml")
public class DetailFinderApplication {
    public static void main(final String[] args) {
        SpringApplication.run(DetailFinderApplication.class, args);
    }
}