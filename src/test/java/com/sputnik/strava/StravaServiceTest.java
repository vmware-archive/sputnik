package com.sputnik.strava;

import com.sputnik.strava.activity.Activity;
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

    @Mock
    ActivityOperations activityOperations;

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
        StravaAthleteProfile stravaAthleteProfile = new StravaAthleteProfile(8, "Fred", "Smith", "freddy@example.com", "example.com/medium.jpg", "example.com/large.jpg");

        doReturn(athleteOperations).when(strava).athleteOperations();
        doReturn(stravaAthleteProfile).when(athleteOperations).getAthleteProfile();

        AthleteProfile athleteProfile = stravaService.getAthleteProfile();

        assertEquals("Fred Smith", athleteProfile.getName());
        assertEquals("freddy@example.com", athleteProfile.getEmail());
        assertEquals("example.com/medium.jpg", athleteProfile.getAvatarMedium());
        assertEquals("example.com/large.jpg", athleteProfile.getAvatarLarge());
    }

    @Test
    public void testGetAthleteProfileNoAvatar() throws Exception {
        StravaAthleteProfile stravaAthleteProfile = new StravaAthleteProfile(8, "Fred", "Smith", "freddy@example.com", "avatar/athlete/medium.png", "avatar/athlete/large.png");

        doReturn(athleteOperations).when(strava).athleteOperations();
        doReturn(stravaAthleteProfile).when(athleteOperations).getAthleteProfile();

        AthleteProfile athleteProfile = stravaService.getAthleteProfile();

        assertEquals(null, athleteProfile.getAvatarMedium());
        assertEquals(null, athleteProfile.getAvatarLarge());
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


    @Test
    public void testGetActivities() throws Exception {
        StravaMap map = new StravaMap("1234", "^&UY^&", 5);

        StravaSegment segment = new StravaSegment(2);
        StravaSegmentEffortAthlete athlete = new StravaSegmentEffortAthlete("9");

        StravaSegmentEffort segmentEffort = new StravaSegmentEffort(8, "Pearl Street", athlete, 23.4F, "2006-04-21T13:20:40Z", segment, 15);

        List<StravaActivity> activities = asList(new StravaActivity(1, "Pearl Street Climb", "Ride", "Best climb ever", 1.0F, 23, "2006-04-21T13:20:40Z", map, asList(segmentEffort)));

        doReturn(activityOperations).when(strava).activityOperations();
        doReturn(activities).when(activityOperations).getAllActivities();

        List<Activity> allActivities = stravaService.getActivities();

        assertEquals(1, allActivities.size());
        Activity activity = allActivities.get(0);

        assertEquals(1, activity.getId());
        assertEquals("Pearl Street Climb", activity.getName());
        assertEquals("Ride", activity.getType());
        assertEquals("Best climb ever", activity.getDescription());
        assertEquals(1.0F, activity.getDistance(), .1);
        assertEquals(23, activity.getElapsedTime());
        assertEquals("2006-04-21T13:20:40Z", activity.getDate());
        assertEquals("^&UY^&", activity.getMapPolyline());

        assertEquals(1, activity.getSegmentEfforts().size());

        SegmentEffort returnedSegmentEffort = activity.getSegmentEfforts().get(0);

        assertEquals(8, returnedSegmentEffort.getId());
    }


    @Test
    public void testGetActivityById() throws Exception {
        StravaMap map = new StravaMap("1234", "^&UY^&", 5);

        StravaSegment segment = new StravaSegment(2);
        StravaSegmentEffortAthlete athlete = new StravaSegmentEffortAthlete("9");

        StravaSegmentEffort segmentEffort = new StravaSegmentEffort(8, "Pearl Street", athlete, 23.4F, "2006-04-21T13:20:40Z", segment, 15);

        StravaActivity activity = new StravaActivity(1, "Pearl Street Climb", "Ride", "Best climb ever", 1.0F, 23, "2006-04-21T13:20:40Z", map, asList(segmentEffort));

        doReturn(activityOperations).when(strava).activityOperations();
        doReturn(activity).when(activityOperations).getActivityById("7");

        Activity returnedActivity = stravaService.getActivityById("7");

        assertEquals(1, returnedActivity.getId());
        assertEquals("Pearl Street Climb", returnedActivity.getName());
        assertEquals("Ride", returnedActivity.getType());
        assertEquals("Best climb ever", returnedActivity.getDescription());
        assertEquals(1.0F, returnedActivity.getDistance(), .1);
        assertEquals(23, returnedActivity.getElapsedTime());
        assertEquals("2006-04-21T13:20:40Z", returnedActivity.getDate());
        assertEquals("^&UY^&", returnedActivity.getMapPolyline());

        assertEquals(1, returnedActivity.getSegmentEfforts().size());

        SegmentEffort returnedSegmentEffort = returnedActivity.getSegmentEfforts().get(0);

        assertEquals(8, returnedSegmentEffort.getId());
    }
}