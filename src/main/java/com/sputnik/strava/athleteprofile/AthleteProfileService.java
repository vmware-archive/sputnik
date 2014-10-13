package com.sputnik.strava.athleteprofile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.strava.api.Strava;
import org.springframework.social.strava.api.StravaAthleteProfile;
import org.springframework.stereotype.Service;

@Service
public class AthleteProfileService {

    private Strava strava;

    @Autowired
    AthleteProfileConverter athleteProfileConverter;

    @Autowired
    public void setStrava(final Strava strava) {
        this.strava = strava;
    }

    public AthleteProfile getAthleteProfile() {
        StravaAthleteProfile stravaAthleteProfile = strava.athleteOperations().getAthleteProfile();

        return athleteProfileConverter.convert(stravaAthleteProfile);
    }

    public AthleteProfile getAthleteProfileById(String id) {
        StravaAthleteProfile stravaAthleteProfile = strava.athleteOperations().getAthleteProfileById(id);

        return athleteProfileConverter.convert(stravaAthleteProfile);
    }
}
