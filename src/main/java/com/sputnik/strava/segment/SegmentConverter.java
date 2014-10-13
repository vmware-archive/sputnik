package com.sputnik.strava.segment;

import com.sputnik.strava.StravaConverter;
import org.springframework.social.strava.api.StravaSegment;
import org.springframework.stereotype.Component;

@Component
public class SegmentConverter extends StravaConverter<StravaSegment, Segment> {
    public Segment convert(StravaSegment stravaSegment) {
        return new Segment(
                stravaSegment.getId(),
                stravaSegment.getName(),
                stravaSegment.getActivityType(),
                stravaSegment.getDistance(),
                stravaSegment.getMap().getPolyline()
        );
    }
}
