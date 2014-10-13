package com.sputnik.strava;

import com.sputnik.strava.activity.Activity;
import com.sputnik.strava.profile.AthleteProfile;
import com.sputnik.strava.segment.Segment;
import com.sputnik.strava.segmenteffort.SegmentEffort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.strava.api.*;
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

        return segmentEffortListCreator(stravaSegmentEfforts);
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

        return segmentEffortListCreator(stravaSegmentEfforts);
    }

    public AthleteProfile getAthleteProfile() {
        StravaAthleteProfile stravaAthleteProfile = strava.athleteOperations().getAthleteProfile();

        return athleteProfileCreator(stravaAthleteProfile);
    }

    public AthleteProfile getAthleteProfileById(String id) {
        StravaAthleteProfile stravaAthleteProfile = strava.athleteOperations().getAthleteProfileById(id);

        return athleteProfileCreator(stravaAthleteProfile);
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

    public List<Activity> getActivities() {
        List<StravaActivity> stravaActivities = strava.activityOperations().getAllActivities();

        return stravaActivities.stream().map(this::activityCreator).collect(Collectors.toList());
    }

    public Activity getActivityById(String id) {
        StravaActivity stravaActivity = strava.activityOperations().getActivityById(id);

        return activityCreator(stravaActivity);
    }

    private List<SegmentEffort> segmentEffortListCreator(List<StravaSegmentEffort> stravaSegmentEfforts) {
        if(stravaSegmentEfforts == null) {
            return new ArrayList<>();
        }
        return stravaSegmentEfforts.stream().map(this::segmentEffortCreator).collect(Collectors.toList());
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

    private Activity activityCreator(StravaActivity stravaActivity) {
        List<SegmentEffort> segmentEfforts = segmentEffortListCreator(stravaActivity.getSegmentEfforts());

        return new Activity(
                stravaActivity.getId(),
                stravaActivity.getName(),
                stravaActivity.getType(),
                stravaActivity.getDescription(),
                stravaActivity.getDistance(),
                stravaActivity.getElapsedTime(),
                stravaActivity.getDate(),
                stravaActivity.getMap().getPolyline(),
                stravaActivity.getMap().getSummaryPolyline(),
                segmentEfforts
        );
    }

    private AthleteProfile athleteProfileCreator(StravaAthleteProfile stravaAthleteProfile) {
        String avatarMedium = (stravaAthleteProfile.getAvatarMedium().equals("avatar/athlete/medium.png")) ? null : stravaAthleteProfile.getAvatarMedium();
        String avatarLarge = (stravaAthleteProfile.getAvatarLarge().equals("avatar/athlete/large.png")) ? null : stravaAthleteProfile.getAvatarLarge();
        
        return new AthleteProfile(
                stravaAthleteProfile.getEmail(),
                stravaAthleteProfile.getName(),
                avatarMedium,
                avatarLarge
        );
    }
}
