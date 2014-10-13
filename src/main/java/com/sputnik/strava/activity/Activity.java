package com.sputnik.strava.activity;

import com.sputnik.strava.segmenteffort.SegmentEffort;

import java.util.List;

public class Activity {
    private long id;
    private String name;
    private String type;
    private String description;
    private float distance;
    private long elapsedTime;
    private String date;
    private String mapPolyline;
    private List<SegmentEffort> segmentEfforts;

    public Activity(long id, String name, String type, String description, float distance, long elapsedTime, String date, String mapPolyline, List<SegmentEffort> segmentEfforts) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.distance = distance;
        this.elapsedTime = elapsedTime;
        this.date = date;
        this.mapPolyline = mapPolyline;
        this.segmentEfforts = segmentEfforts;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public float getDistance() {
        return distance;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public String getDate() {
        return date;
    }

    public String getMapPolyline() {
        return mapPolyline;
    }

    public List<SegmentEffort> getSegmentEfforts() {
        return segmentEfforts;
    }
}
