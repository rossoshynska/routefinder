package com.goeuro.service;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import static org.assertj.core.api.Assertions.assertThat;

public class RouteServiceTest {

    private RouteService routeService = new RouteService();

    @Before
    public void setup() throws IOException {
        final ClassPathResource resource = new ClassPathResource("/routes");
        routeService.setRouteFile(new File(resource.getURI()).getAbsolutePath());
    }

    @Test
    public void testDirectRouteFound() {
        boolean directRouteExists = routeService.directRouteExists(3, 6);

        assertThat(directRouteExists).isTrue();
    }

    @Test
    public void testDirectRouteNotFound() {
        boolean directRouteExists = routeService.directRouteExists(0, 5);

        assertThat(directRouteExists).isFalse();
    }

    @Test
    public void testStopNotFound() {
        boolean directRouteExists = routeService.directRouteExists(2, 7);

        assertThat(directRouteExists).isFalse();
    }
}
