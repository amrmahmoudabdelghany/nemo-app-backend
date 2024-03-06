package com.store.nemo.api;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.store.nemo.api" , "com.store.nemo.repositories"})
public class ApiConfig {
    
}
