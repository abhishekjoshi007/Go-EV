package com.sih.goev.utility;

import com.google.type.LatLng;

public class RouteSegment {

    private LatLng start;
    private LatLng end;
    private float segmentTrafficLevel;
    private float segmentLength;

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

    public float getSegmentTrafficLevel() {
        return segmentTrafficLevel;
    }

    public void setSegmentTrafficLevel(float segmentTrafficLevel) {
        this.segmentTrafficLevel = segmentTrafficLevel;
    }

    public float getSegmentLength() {
        return segmentLength;
    }

    public void setSegmentLength(float segmentLength) {
        this.segmentLength = segmentLength;
    }
}
