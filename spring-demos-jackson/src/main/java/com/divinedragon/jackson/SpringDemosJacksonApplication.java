package com.divinedragon.jackson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDemosJacksonApplication {

    public static final String DATE_TIME_FORMAT_STRING = "yyyy-MM-dd'T'HH:mm:ssXXX";

    public static void main(final String[] args) {
        SpringApplication.run(SpringDemosJacksonApplication.class, args);
    }
}
