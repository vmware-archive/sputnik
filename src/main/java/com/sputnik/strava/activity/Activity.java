package com.sputnik.strava.activity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sputnik.strava.segmenteffort.SegmentEffort;

import java.util.List;
import java.util.stream.Collectors;

public class Activity {
    private long id;
    private String name;
    private String type;
    private String description;
    private float distance;
    private long elapsedTime;
    private String date;
    private String mapPolyline;
    private String mapSummaryPolyline;
    private List<SegmentEffort> segmentEfforts;

    public Activity(long id, String name, String type, String description, float distance, long elapsedTime, String date, String mapPolyline, String mapSummaryPolyline, List<SegmentEffort> segmentEfforts) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.distance = distance;
        this.elapsedTime = elapsedTime;
        this.date = date;
        this.mapPolyline = mapPolyline;
        this.mapSummaryPolyline = mapSummaryPolyline;
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

    public String getMapSummaryPolyline() {
        return mapSummaryPolyline;
    }

    public List<SegmentEffort> getSegmentEfforts() {
        return segmentEfforts;
    }

    @JsonIgnore
    public List<Long> getSegmentIds() {
        return segmentEfforts.stream().map(SegmentEffort::getSegmentId).collect(Collectors.toList());
    }
}
