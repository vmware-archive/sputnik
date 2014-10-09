package com.sputnik.strava;

import com.sputnik.strava.profile.AthleteProfile;
import com.sputnik.strava.segment.Segment;
import com.sputnik.strava.segmenteffort.SegmentEffort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.strava.api.Strava;
import org.springframework.social.strava.api.StravaAthleteProfile;
import org.springframework.social.strava.api.StravaSegment;
import org.springframework.social.strava.api.StravaSegmentEffort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StravaService {

    private Strava strava;

    private String[] segmentIds;

    @Autowired
    public void setStrava(final Strava strava) {
        this.strava = strava;
    }

    @Value("${sponsoredSegments}")
    public void setSegmentIds(final String[] segmentIds) {
        this.segmentIds = segmentIds;
    }

    public List<SegmentEffort> getSegmentEfforts() {
        String currentAthleteId = String.valueOf(strava.athleteOperations().getAthleteProfile().getId());

        List<StravaSegmentEffort> stravaSegmentEfforts = new ArrayList<>();

        for(String id : segmentIds) {
            stravaSegmentEfforts.addAll(strava.segmentEffortOperations().getSegmentEfforts(id, currentAthleteId));
        }

        return stravaSegmentEfforts.stream().map(this::segmentEffortCreator).collect(Collectors.toList());
    }

    public SegmentEffort getSegmentEffortById(String id) {
        StravaSegmentEffort segmentEffort = strava.segmentEffortOperations().getSegmentEffortById(id);
        return segmentEffortCreator(segmentEffort);
    }

    public List<SegmentEffort> getAllSegmentEfforts(String startTime, String endTime) {
        List<StravaSegmentEffort> stravaSegmentEfforts = new ArrayList<>();

        for(String id : segmentIds) {
            stravaSegmentEfforts.addAll(strava.segmentEffortOperations().getAllSegmentEfforts(id, startTime, endTime));
        }

        return stravaSegmentEfforts.stream().map(this::segmentEffortCreator).collect(Collectors.toList());
    }

    public AthleteProfile getAthleteProfile() {
        StravaAthleteProfile stravaAthleteProfile = strava.athleteOperations().getAthleteProfile();

        return new AthleteProfile(stravaAthleteProfile.getEmail(), stravaAthleteProfile.getName());
    }

    private SegmentEffort segmentEffortCreator(StravaSegmentEffort stravaSegmentEffort) {
        return new SegmentEffort(
                stravaSegmentEffort.getId(),
                stravaSegmentEffort.getName(),
                stravaSegmentEffort.getAthlete().getId(),
                stravaSegmentEffort.getDistance(),
                stravaSegmentEffort.getDate(),
                stravaSegmentEffort.getSegment().getId(),
                stravaSegmentEffort.getElapsedTime()
        );
    }

    public Segment getSegmentById(String id) {
        StravaSegment stravaSegment = strava.segmentOperations().getSegmentById(id);
        return new Segment(
                stravaSegment.getId(),
                stravaSegment.getName(),
                stravaSegment.getActivityType(),
                stravaSegment.getDistance(),
                stravaSegment.getMap().getPolyline()
        );
    }
}
