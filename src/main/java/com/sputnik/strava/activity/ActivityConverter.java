package com.sputnik.strava.activity;

import com.sputnik.strava.StravaConverter;
import com.sputnik.strava.segmenteffort.SegmentEffort;
import com.sputnik.strava.segmenteffort.SegmentEffortConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.strava.api.StravaActivity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActivityConverter extends StravaConverter<StravaActivity, Activity> {

    @Autowired
    SegmentEffortConverter segmentEffortConverter;

    @Override
    public Activity convert(StravaActivity stravaActivity) {
        List<SegmentEffort> segmentEfforts = segmentEffortConverter.convertList(stravaActivity.getSegmentEfforts());

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
}
