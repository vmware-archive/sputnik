package com.sputnik.strava.profile;

import com.sputnik.strava.StravaConverter;
import org.springframework.social.strava.api.StravaAthleteProfile;
import org.springframework.stereotype.Component;

@Component
public class AthleteProfileConverter extends StravaConverter<StravaAthleteProfile, AthleteProfile> {
    public AthleteProfile convert(StravaAthleteProfile stravaAthleteProfile) {
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
