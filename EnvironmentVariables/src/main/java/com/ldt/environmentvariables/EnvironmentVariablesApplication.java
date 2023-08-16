package com.ldt.environmentvariables;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EnvironmentVariablesApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnvironmentVariablesApplication.class, args);
    }

    @Value("${my.data}")
    String myData;

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            System.out.println("myData = " + myData);
        };
    }
}
