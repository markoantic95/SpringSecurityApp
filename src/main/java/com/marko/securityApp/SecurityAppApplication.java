package com.marko.securityApp;

import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.convert.Jsr310Converters;

@SpringBootApplication
@EntityScan(basePackageClasses = {SecurityAppApplication.class, Jsr310Converters.class})
public class SecurityAppApplication {

    //Spring calls methods annotated with @PostConstruct only once, just after the initialization of bean properties. (after dependency injection)
    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC")); // set time zone to UTC
    }

    public static void main(String[] args) {
        SpringApplication.run(SecurityAppApplication.class, args);
    }

}

// JSR 310 - API for time- and calendar-related calculations. It aims to  replace the two existing classes 
// that form Java's current date and time API,java.util.Date and java.util.Calendar