package com.sputnik.campaign;

import com.sputnik.strava.activity.Activity;
import com.sputnik.strava.activity.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampaignService {

    @Autowired
    ActivityService activityService;

    @Autowired
    CampaignRepository campaignRepository;

    public Iterable<Campaign> findAll() {
        return campaignRepository.findAll();
    }

    public Iterable<Campaign> findForActivityId(String activityId) {
        Activity activity = activityService.getActivityById(activityId);

        return campaignRepository.findForSegmentIds(activity.getSegmentIds());
    }

}
