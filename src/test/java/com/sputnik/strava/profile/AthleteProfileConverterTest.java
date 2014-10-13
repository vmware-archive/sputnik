package com.sputnik.strava.profile;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.social.strava.api.StravaAthleteProfile;

@RunWith(MockitoJUnitRunner.class)
public class AthleteProfileConverterTest extends TestCase {

    @InjectMocks
    AthleteProfileConverter athleteProfileConverter;

    @Test
    public void testConvert() throws Exception {
        StravaAthleteProfile stravaAthleteProfile = new StravaAthleteProfile(8, "Fred", "Smith", "freddy@example.com", "example.com/medium.jpg", "example.com/large.jpg");

        AthleteProfile athleteProfile = athleteProfileConverter.convert(stravaAthleteProfile);

        assertEquals("Fred Smith", athleteProfile.getName());
        assertEquals("freddy@example.com", athleteProfile.getEmail());
        assertEquals("example.com/medium.jpg", athleteProfile.getAvatarMedium());
        assertEquals("example.com/large.jpg", athleteProfile.getAvatarLarge());
    }

    @Test
    public void testConvertNoImages() throws Exception {
        StravaAthleteProfile stravaAthleteProfile = new StravaAthleteProfile(8, "Fred", "Smith", "freddy@example.com", "avatar/athlete/medium.png", "avatar/athlete/large.png");

        AthleteProfile athleteProfile = athleteProfileConverter.convert(stravaAthleteProfile);

        assertEquals("Fred Smith", athleteProfile.getName());
        assertEquals("freddy@example.com", athleteProfile.getEmail());
        assertEquals(null, athleteProfile.getAvatarMedium());
        assertEquals(null, athleteProfile.getAvatarLarge());
    }
}