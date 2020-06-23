package com.avito.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;

@Configuration
public class DataInitializer {

    @EventListener
    public void handleContextStart(ContextStartedEvent event) {
        System.out.println("Application START !!! ");
    }

}
