package com.goeuro.web;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RouteDto {

    @JsonProperty("dep_sid")
    private int departureId;

    @JsonProperty("arr_sid")
    private int arrivalId;

    @JsonProperty("direct_bus_route")
    private boolean directRoute;

    public RouteDto() {
    }

    public RouteDto(final int departureId, final int arrivalId, final boolean directRoute) {
        this.departureId = departureId;
        this.arrivalId = arrivalId;
        this.directRoute = directRoute;
    }

    public int getDepartureId() {
        return departureId;
    }

    public void setDepartureId(final int departureId) {
        this.departureId = departureId;
    }

    public int getArrivalId() {
        return arrivalId;
    }

    public void setArrivalId(final int arrivalId) {
        this.arrivalId = arrivalId;
    }

    public boolean isDirectRoute() {
        return directRoute;
    }

    public void setDirectRoute(final boolean directRoute) {
        this.directRoute = directRoute;
    }
}
