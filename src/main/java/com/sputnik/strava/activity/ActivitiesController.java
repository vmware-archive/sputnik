package com.sputnik.strava.activity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ActivitiesController {
    @Autowired
    private ActivityService activityService;

    @RequestMapping(method = RequestMethod.GET, value = "/strava/activities")
    public
    @ResponseBody
    Iterable<Activity> getAllActivities() {
        return activityService.getActivities();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/strava/activities/{id}")
    public
    @ResponseBody
    Activity getActivityById(@PathVariable String id) {
        return activityService.getActivityById(id);
    }
}
