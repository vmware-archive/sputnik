package com.sputnik.strava.segmenteffort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.strava.api.Strava;
import org.springframework.social.strava.api.StravaSegmentEffort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SegmentEffortService {

    private Strava strava;

    private String[] segmentIds;

    private SegmentEffortConverter segmentEffortConverter;

    @Autowired
    public void setStrava(final Strava strava) {
        this.strava = strava;
    }

    @Value("${sponsoredSegments}")
    public void setSegmentIds(final String[] segmentIds) {
        this.segmentIds = segmentIds;
    }

    @Autowired
    public void setSegmentEffortConverter(SegmentEffortConverter segmentEffortConverter) {
        this.segmentEffortConverter = segmentEffortConverter;
    }

    public List<SegmentEffort> getSegmentEfforts() {
        String currentAthleteId = String.valueOf(strava.athleteOperations().getAthleteProfile().getId());

        List<StravaSegmentEffort> stravaSegmentEfforts = new ArrayList<>();

        for(String id : segmentIds) {
            stravaSegmentEfforts.addAll(strava.segmentEffortOperations().getSegmentEfforts(id, currentAthleteId));
        }

        return segmentEffortConverter.convertList(stravaSegmentEfforts);
    }

    public SegmentEffort getSegmentEffortById(String id) {
        StravaSegmentEffort segmentEffort = strava.segmentEffortOperations().getSegmentEffortById(id);
        return segmentEffortConverter.convert(segmentEffort);
    }

    public List<SegmentEffort> getAllSegmentEfforts(String startTime, String endTime) {
        List<StravaSegmentEffort> stravaSegmentEfforts = new ArrayList<>();

        for(String id : segmentIds) {
            stravaSegmentEfforts.addAll(strava.segmentEffortOperations().getAllSegmentEfforts(id, startTime, endTime));
        }

        return segmentEffortConverter.convertList(stravaSegmentEfforts);
    }
}
