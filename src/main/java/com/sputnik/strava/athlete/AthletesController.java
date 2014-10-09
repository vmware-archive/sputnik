package com.sputnik.strava.athlete;

import com.sputnik.strava.StravaService;
import com.sputnik.strava.profile.AthleteProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AthletesController {

    @Autowired
    private StravaService stravaService;

    @RequestMapping(method = RequestMethod.GET, value = "/strava/athletes/{id}")
    public
    @ResponseBody
    AthleteProfile getAthleteProfileById(@PathVariable String id) {
        return stravaService.getAthleteProfileById(id);
    }
}
