package com.sih.goev.utility;

import com.google.type.LatLng;

import java.util.ArrayList;

public class Route {

    private LatLng start;
    private LatLng end;
    private float routeLength;
    private ArrayList<RouteSegment> routeSegments;
    private int numberOfSegments;

    public LatLng getStart() {
        return start;
    }

    public void setStart(LatLng start) {
        this.start = start;
    }

    public LatLng getEnd() {
        return end;
    }

    public void setEnd(LatLng end) {
        this.end = end;
    }

    public float getRouteLength() {
        return routeLength;
    }

    public void setRouteLength(float routeLength) {
        this.routeLength = routeLength;
    }

    public ArrayList<RouteSegment> getRouteSegments() {
        return routeSegments;
    }

    public void setRouteSegments(ArrayList<RouteSegment> routeSegments) {
        this.routeSegments = routeSegments;
    }

    public int getNumberOfSegments() {
        return numberOfSegments;
    }

    public void setNumberOfSegments(int numberOfSegments) {
        this.numberOfSegments = numberOfSegments;
    }
}
