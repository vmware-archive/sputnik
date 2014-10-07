package com.sputnik.strava;

import com.sputnik.strava.profile.AthleteProfile;
import com.sputnik.strava.segmenteffort.SegmentEffort;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.social.strava.api.*;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class StravaServiceTest extends TestCase {
    @Mock
    Strava strava;

    @Mock
    AthleteOperations athleteOperations;

    @Mock
    SegmentEffortOperations segmentEffortOperations;

    @Mock
    StravaAthleteProfile profile;

    @InjectMocks
    StravaService stravaService;

    @Test
    public void testGetSegmentEfforts() throws Exception {
        doReturn(athleteOperations).when(strava).athleteOperations();
        doReturn(profile).when(athleteOperations).getAthleteProfile();
        doReturn(567L).when(profile).getId();

        StravaSegment segment = new StravaSegment(2);
        StravaSegmentEffortAthlete athlete = new StravaSegmentEffortAthlete(9);
        StravaSegmentEffort segmentEffort = new StravaSegmentEffort(8, "Pearl Street", athlete, 23.4F, "2006-04-21T13:20:40Z", segment, 15);

        doReturn(segmentEffortOperations).when(strava).segmentEffortOperations();
        doReturn(asList(segmentEffort)).when(segmentEffortOperations).getSegmentEfforts("8269800", "567");

        List<SegmentEffort> segmentEfforts = stravaService.getSegmentEfforts();

        assertEquals(1, segmentEfforts.size());

        SegmentEffort returnedSegmentEffort = segmentEfforts.get(0);

        assertEquals(8, returnedSegmentEffort.getId());
        assertEquals("Pearl Street", returnedSegmentEffort.getName());
        assertEquals(9, returnedSegmentEffort.getAthleteId());
        assertEquals(23.4, returnedSegmentEffort.getDistance(), .1);
        assertEquals("2006-04-21T13:20:40Z", returnedSegmentEffort.getDate());
        assertEquals(2, returnedSegmentEffort.getSegmentId());
        assertEquals(15, returnedSegmentEffort.getElapsedTime());
    }

    @Test
    public void testGetAthleteProfile() throws Exception {
        StravaAthleteProfile stravaAthleteProfile = new StravaAthleteProfile(8, "Fred", "Smith", "freddy@example.com");

        doReturn(athleteOperations).when(strava).athleteOperations();
        doReturn(stravaAthleteProfile).when(athleteOperations).getAthleteProfile();

        AthleteProfile athleteProfile = stravaService.getAthleteProfile();

        assertEquals("Fred Smith", athleteProfile.getName());
        assertEquals("freddy@example.com", athleteProfile.getEmail());
    }
}