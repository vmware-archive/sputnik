package com.sputnik.strava.athleteprofile;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.social.strava.api.AthleteOperations;
import org.springframework.social.strava.api.Strava;
import org.springframework.social.strava.api.StravaAthleteProfile;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class AthleteProfileServiceTest extends TestCase {
    @Mock
    Strava strava;

    @Mock
    AthleteOperations athleteOperations;

    @Mock
    AthleteProfileConverter athleteProfileConverter;

    @InjectMocks
    AthleteProfileService athleteProfileService;

    @Test
    public void testGetAthleteProfile() throws Exception {
        StravaAthleteProfile stravaAthleteProfile = mock(StravaAthleteProfile.class);
        AthleteProfile athleteProfile = mock(AthleteProfile.class);

        doReturn(athleteOperations).when(strava).athleteOperations();
        doReturn(stravaAthleteProfile).when(athleteOperations).getAthleteProfile();
        doReturn(athleteProfile).when(athleteProfileConverter).convert(stravaAthleteProfile);

        AthleteProfile returnedAthleteProfile = athleteProfileService.getAthleteProfile();

        assertEquals(athleteProfile, returnedAthleteProfile);
    }

    @Test
    public void testGetAthleteProfileById() throws Exception {
        StravaAthleteProfile stravaAthleteProfile = mock(StravaAthleteProfile.class);
        AthleteProfile athleteProfile = mock(AthleteProfile.class);

        doReturn(athleteOperations).when(strava).athleteOperations();
        doReturn(stravaAthleteProfile).when(athleteOperations).getAthleteProfileById("17");
        doReturn(athleteProfile).when(athleteProfileConverter).convert(stravaAthleteProfile);

        AthleteProfile returnedAthleteProfile = athleteProfileService.getAthleteProfileById("17");

        assertEquals(athleteProfile, returnedAthleteProfile);
    }
}