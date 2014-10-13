package com.sputnik.strava.segment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.strava.api.Strava;
import org.springframework.social.strava.api.StravaSegment;
import org.springframework.stereotype.Service;

@Service
public class SegmentService {

    private Strava strava;

    @Autowired
    SegmentConverter segmentConverter;

    @Autowired
    public void setStrava(final Strava strava) {
        this.strava = strava;
    }

    public Segment getSegmentById(String id) {
        StravaSegment stravaSegment = strava.segmentOperations().getSegmentById(id);
        return segmentConverter.convert(stravaSegment);
    }
}
