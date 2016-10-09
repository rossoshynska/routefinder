package com.goeuro;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.goeuro.config.ExternalConfig;

@SpringBootApplication
public class RoutefinderApplication {

    @Autowired
    private ExternalConfig externalConfig;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(new Object[]{RoutefinderApplication.class});

        final String filePathString = args[0];

        File f = new File(filePathString);
        if (!f.exists() || f.isDirectory()) {
            throw new RuntimeException("Routes file not found: " + filePathString);
        }
        Map<String, Object> defaultProperties = new HashMap<String, Object>();
        defaultProperties.put("routeFileLocation", filePathString);
        springApplication.setDefaultProperties(defaultProperties);

        springApplication.run(args);
    }
}
