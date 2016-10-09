package com.goeuro.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RouteService {

    private static Logger logger = LoggerFactory.getLogger(RouteService.class);

    private Map<Integer, Set<Integer>> stops;

    private String routeFile;

    public boolean directRouteExists(final int departureId, final int arrivalId) {
        final Set<Integer> departureRoutes = getRoutes(departureId);
        final Set<Integer> arrivalRoutes = getRoutes(arrivalId);
        if (departureRoutes == null || arrivalRoutes == null) {
            return false;
        }
        final Set<Integer> intersection = new HashSet<>(departureRoutes);
        intersection.retainAll(arrivalRoutes);
        return !intersection.isEmpty();
    }

    private Set<Integer> getRoutes(Integer stopId) {
        if (stops == null) {
            synchronized (this) {
                Map<Integer, Set<Integer>> stopsInfo = new HashMap<>();

                try (Stream<String> stream = Files.lines(Paths.get(routeFile))) {
                    stream.skip(1)
                          .forEach(line -> addRoute(stopsInfo, line));

                    stops = stopsInfo;
                } catch (IOException | NumberFormatException e) {
                    logger.error(e.getMessage(), e);
                    throw new RouteSearchException("Could not perform route search: " + e.getMessage());
                }
            }
        }
        return stops.get(stopId);
    }

    private void addRoute(final Map<Integer, Set<Integer>> stopsInfo, final String routeString) {
        final String[] splitted = routeString.split(" ");
        if (splitted.length <= 1) {
            return;
        }
        final Integer routeId = Integer.parseInt(splitted[0]);
        Arrays.stream(splitted, 1, splitted.length)
              .map(stopString -> Integer.parseInt(stopString))
              .forEach(stop -> {
                  if (!stopsInfo.containsKey(stop)) {
                      stopsInfo.put(stop, new HashSet<>());
                  }
                  stopsInfo.get(stop).add(routeId);
              });
    }

    @Value("${routeFileLocation}")
    public void setRouteFile(final String routeFile) {
        this.routeFile = routeFile;
    }
}
