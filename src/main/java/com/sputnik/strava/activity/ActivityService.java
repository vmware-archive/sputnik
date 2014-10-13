package com.sputnik.strava.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.strava.api.Strava;
import org.springframework.social.strava.api.StravaActivity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    private Strava strava;

    @Autowired
    ActivityConverter activityConverter;

    @Autowired
    public void setStrava(final Strava strava) {
        this.strava = strava;
    }

    public List<Activity> getActivities() {
        List<StravaActivity> stravaActivities = strava.activityOperations().getAllActivities();

        return activityConverter.convertList(stravaActivities);
    }

    public Activity getActivityById(String id) {
        StravaActivity stravaActivity = strava.activityOperations().getActivityById(id);

        return activityConverter.convert(stravaActivity);
    }
}
