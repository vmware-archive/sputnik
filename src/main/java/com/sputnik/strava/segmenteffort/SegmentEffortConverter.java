package com.sputnik.strava.segmenteffort;

import com.sputnik.strava.StravaConverter;
import org.springframework.social.strava.api.StravaSegmentEffort;
import org.springframework.stereotype.Component;

@Component
public class SegmentEffortConverter extends StravaConverter<StravaSegmentEffort, SegmentEffort>{

    public SegmentEffort convert(StravaSegmentEffort stravaSegmentEffort) {
        return new SegmentEffort(
                stravaSegmentEffort.getId(),
                stravaSegmentEffort.getName(),
                stravaSegmentEffort.getAthlete().getId(),
                stravaSegmentEffort.getDistance(),
                stravaSegmentEffort.getDate(),
                stravaSegmentEffort.getSegment().getId(),
                stravaSegmentEffort.getElapsedTime(),
                stravaSegmentEffort.getActivity().getId()
        );
    }
}
