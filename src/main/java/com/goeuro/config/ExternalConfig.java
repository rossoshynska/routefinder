package com.goeuro.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExternalConfig {

    private static Logger logger = LoggerFactory.getLogger(ExternalConfig.class);

    @Value("${routeFileLocation}")
    public String routeFileLocation;

    @PostConstruct
    public void postConstruct() {
        logger.info("RouteFileLocation: " + routeFileLocation);
    }

}
