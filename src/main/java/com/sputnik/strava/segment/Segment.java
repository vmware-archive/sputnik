package com.sputnik.strava.segment;

public class Segment {
    private long id;
    private String name;
    private String activityType;
    private Float distance;
    private String mapPolyline;

    public Segment(long id, String name, String activityType, Float distance, String mapPolyline) {
        this.id = id;
        this.name = name;
        this.activityType = activityType;
        this.distance = distance;
        this.mapPolyline = mapPolyline;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getActivityType() {
        return activityType;
    }

    public Float getDistance() {
        return distance;
    }

    public String getMapPolyline() {
        return mapPolyline;
    }
}
