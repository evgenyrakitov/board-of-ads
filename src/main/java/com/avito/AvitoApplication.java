package com.avito;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AvitoApplication {
    private static final Logger logger = LoggerFactory.getLogger(AvitoApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(AvitoApplication.class, args).start();
    }

}
