package com.sputnik.strava.athleteprofile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProfileController {

    @Autowired
    private AthleteProfileService athleteProfileService;

    @RequestMapping(method = RequestMethod.GET, value = "/strava/profile")
    public
    @ResponseBody
    AthleteProfile getAthleteProfile() {
        return athleteProfileService.getAthleteProfile();
    }
}
