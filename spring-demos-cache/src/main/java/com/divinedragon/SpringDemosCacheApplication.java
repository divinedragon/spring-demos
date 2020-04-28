package com.divinedragon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication()
@EnableCaching
public class SpringDemosCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDemosCacheApplication.class, args);
    }
}
