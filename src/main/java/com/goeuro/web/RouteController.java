package com.goeuro.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.goeuro.service.RouteSearchException;
import com.goeuro.service.RouteService;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class RouteController {

    private RouteService routeService;

    @RequestMapping(value = "/direct", method = RequestMethod.GET)
    public RouteDto getDirectRoute(@RequestParam(name = "dep_sid", required = true) final int departureId,
                                   @RequestParam(name = "arr_sid", required = true) final int arrivalId) {
        boolean directRouteExists = routeService.directRouteExists(departureId, arrivalId);
        return new RouteDto(departureId, arrivalId, directRouteExists);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public
    @ResponseBody
    ApiError handleIllegalArgumentException(HttpServletRequest req, Exception ex) {
        return new ApiError(req.getRequestURL().toString(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RouteSearchException.class)
    public
    @ResponseBody
    ApiError handleWebAnalysisException(HttpServletRequest req, Exception ex) {
        return new ApiError(req.getRequestURL().toString(), ex.getMessage());
    }

    @Autowired
    public void setRouteService(final RouteService routeService) {
        this.routeService = routeService;
    }
}
