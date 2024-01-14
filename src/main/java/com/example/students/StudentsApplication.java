package com.example.students;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//Włączamy korzystanie z feignowych klientów webowych
//Warto zapamiętać, że jest to standardowy sposób uruchamiania różnych Springowych funkcjonalności
@EnableFeignClients
@SpringBootApplication
public class StudentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentsApplication.class, args);
    }

}
