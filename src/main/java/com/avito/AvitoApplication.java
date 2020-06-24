package com.avito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class AvitoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AvitoApplication.class, args);
    }

}
