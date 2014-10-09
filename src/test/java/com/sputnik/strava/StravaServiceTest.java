package com.sputnik.strava;

import com.sputnik.strava.profile.AthleteProfile;
import com.sputnik.strava.segment.Segment;
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
    SegmentOperations segmentOperations;

    @Mock
    StravaAthleteProfile profile;

    @InjectMocks
    StravaService stravaService;

    @Test
    public void testGetSegmentEfforts() throws Exception {
        String[] segmentIds = {"8269800", "4784391"};
        stravaService.setSegmentIds(segmentIds);


        doReturn(athleteOperations).when(strava).athleteOperations();
        doReturn(profile).when(athleteOperations).getAthleteProfile();
        doReturn(567L).when(profile).getId();

        StravaSegment segment = new StravaSegment(2);
        StravaSegmentEffortAthlete athlete = new StravaSegmentEffortAthlete("9");

        StravaSegmentEffort segmentEffort = new StravaSegmentEffort(8, "Pearl Street", athlete, 23.4F, "2006-04-21T13:20:40Z", segment, 15);
        StravaSegmentEffort anotherSegmentEffort = new StravaSegmentEffort(8, "Spruce Street", athlete, 13.4F, "2006-05-21T13:20:40Z", segment, 25);

        doReturn(segmentEffortOperations).when(strava).segmentEffortOperations();

        doReturn(asList(segmentEffort)).when(segmentEffortOperations).getSegmentEfforts("8269800", "567");
        doReturn(asList(anotherSegmentEffort)).when(segmentEffortOperations).getSegmentEfforts("4784391", "567");

        List<SegmentEffort> segmentEfforts = stravaService.getSegmentEfforts();

        assertEquals(2, segmentEfforts.size());

        SegmentEffort returnedSegmentEffort = segmentEfforts.get(0);

        assertEquals(8, returnedSegmentEffort.getId());
        assertEquals("Pearl Street", returnedSegmentEffort.getName());
        assertEquals("9", returnedSegmentEffort.getAthleteId());
        assertEquals(23.4, returnedSegmentEffort.getDistance(), .1);
        assertEquals("2006-04-21T13:20:40Z", returnedSegmentEffort.getDate());
        assertEquals(2, returnedSegmentEffort.getSegmentId());
        assertEquals(15, returnedSegmentEffort.getElapsedTime());

        SegmentEffort anotherReturnedSegmentEffort = segmentEfforts.get(1);
        assertEquals("Spruce Street", anotherReturnedSegmentEffort.getName());
    }

    @Test
    public void testGetSegmentEffortById() throws Exception {
        StravaSegment segment = new StravaSegment(2);
        StravaSegmentEffortAthlete athlete = new StravaSegmentEffortAthlete("9");
        StravaSegmentEffort segmentEffort = new StravaSegmentEffort(8, "Pearl Street", athlete, 23.4F, "2006-04-21T13:20:40Z", segment, 15);

        doReturn(segmentEffortOperations).when(strava).segmentEffortOperations();
        doReturn(segmentEffort).when(segmentEffortOperations).getSegmentEffortById("1234567");

        SegmentEffort returnedSegmentEffort = stravaService.getSegmentEffortById("1234567");

        assertEquals(8, returnedSegmentEffort.getId());
        assertEquals("Pearl Street", returnedSegmentEffort.getName());
        assertEquals("9", returnedSegmentEffort.getAthleteId());
        assertEquals(23.4, returnedSegmentEffort.getDistance(), .1);
        assertEquals("2006-04-21T13:20:40Z", returnedSegmentEffort.getDate());
        assertEquals(2, returnedSegmentEffort.getSegmentId());
        assertEquals(15, returnedSegmentEffort.getElapsedTime());
    }

    public void testGetAllSegmentEfforts() throws Exception {
        String[] segmentIds = {"8269800", "4784391"};
        stravaService.setSegmentIds(segmentIds);

        StravaSegment segment = new StravaSegment(2);
        StravaSegmentEffortAthlete athlete = new StravaSegmentEffortAthlete("9");

        StravaSegmentEffort segmentEffort = new StravaSegmentEffort(8, "Pearl Street", athlete, 23.4F, "2006-04-21T13:20:40Z", segment, 15);
        StravaSegmentEffort anotherSegmentEffort = new StravaSegmentEffort(8, "Spruce Street", athlete, 13.4F, "2006-05-21T13:20:40Z", segment, 25);

        doReturn(segmentEffortOperations).when(strava).segmentEffortOperations();

        doReturn(asList(segmentEffort)).when(segmentEffortOperations).getAllSegmentEfforts("8269800", "2006-04-21T13:20:40Z", "2006-04-22T13:20:40Z");
        doReturn(asList(anotherSegmentEffort)).when(segmentEffortOperations).getAllSegmentEfforts("4784391", "2006-04-21T13:20:40Z", "2006-04-22T13:20:40Z");

        List<SegmentEffort> segmentEfforts = stravaService.getAllSegmentEfforts("2006-04-21T13:20:40Z", "2006-04-22T13:20:40Z");

        assertEquals(2, segmentEfforts.size());

        SegmentEffort returnedSegmentEffort = segmentEfforts.get(0);

        assertEquals(8, returnedSegmentEffort.getId());
        assertEquals("Pearl Street", returnedSegmentEffort.getName());
        assertEquals("9", returnedSegmentEffort.getAthleteId());
        assertEquals(23.4, returnedSegmentEffort.getDistance(), .1);
        assertEquals("2006-04-21T13:20:40Z", returnedSegmentEffort.getDate());
        assertEquals(2, returnedSegmentEffort.getSegmentId());
        assertEquals(15, returnedSegmentEffort.getElapsedTime());

        SegmentEffort anotherReturnedSegmentEffort = segmentEfforts.get(1);
        assertEquals("Spruce Street", anotherReturnedSegmentEffort.getName());
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

    @Test
    public void testGetSegmentById() throws Exception {
        StravaMap map = new StravaMap("1234", "^&UY^&", 5);
        StravaSegment segment = new StravaSegment(2, "Pearl Street", "foosball", 123.4F, map);
        
        doReturn(segmentOperations).when(strava).segmentOperations();
        doReturn(segment).when(segmentOperations).getSegmentById("1234567");

        Segment returnedSegment = stravaService.getSegmentById("1234567");

        assertEquals(2, returnedSegment.getId());
        assertEquals("Pearl Street", returnedSegment.getName());
        assertEquals("foosball", returnedSegment.getActivityType());
        assertEquals(123.4, returnedSegment.getDistance(), .1);
        assertEquals("^&UY^&", returnedSegment.getMapPolyline());
    }
}