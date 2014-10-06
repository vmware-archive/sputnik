package com.sputnik.strava.segmenteffort;

public class SegmentEffort {
    private long id;
    private String name;
    private long athleteId;
    private float distance;
    private String date;
    private long segmentId;
    private long elapsedTime;

    public SegmentEffort(long id, String name, long athleteId, float distance, String date, long segmentId, long elapsedTime) {
        this.id = id;
        this.name = name;
        this.athleteId = athleteId;
        this.distance = distance;
        this.date = date;
        this.segmentId = segmentId;
        this.elapsedTime = elapsedTime;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getAthleteId() {
        return athleteId;
    }

    public float getDistance() {
        return distance;
    }

    public String getDate() {
        return date;
    }

    public long getSegmentId() {
        return segmentId;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }
}
