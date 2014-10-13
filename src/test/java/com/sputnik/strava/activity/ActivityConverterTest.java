package com.sputnik.strava.activity;

import com.sputnik.strava.segmenteffort.SegmentEffort;
import com.sputnik.strava.segmenteffort.SegmentEffortConverter;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.social.strava.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.codehaus.groovy.runtime.InvokerHelper.asList;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class ActivityConverterTest extends TestCase {

    @Mock
    SegmentEffortConverter segmentEffortConverter;

    @InjectMocks
    ActivityConverter activityConverter;

    @Test
    public void convertSingle() throws Exception {
        List segmentEfforts = asList(mock(SegmentEffort.class));
        doReturn(segmentEfforts).when(segmentEffortConverter).convertList(anyListOf(StravaSegmentEffort.class));

        StravaMap map = new StravaMap("1234", "^&UY^&", "ABC", 5);

        StravaSegment segment = new StravaSegment(2);
        StravaSegmentEffortAthlete athlete = new StravaSegmentEffortAthlete("9");

        StravaActivity dummyActivity = mock(StravaActivity.class);

        StravaSegmentEffort segmentEffort = new StravaSegmentEffort(8, "Pearl Street", athlete, 23.4F, "2006-04-21T13:20:40Z", segment, 15, dummyActivity);

        StravaActivity activity = new StravaActivity(1, "Pearl Street Climb", "Ride", "Best climb ever", 1.0F, 23, "2006-04-21T13:20:40Z", map, asList(segmentEffort));


        Activity convertedActivity = activityConverter.convert(activity);

        assertEquals(1, convertedActivity.getId());
        assertEquals("Pearl Street Climb", convertedActivity.getName());
        assertEquals("Ride", convertedActivity.getType());
        assertEquals("Best climb ever", convertedActivity.getDescription());
        assertEquals(1.0F, convertedActivity.getDistance(), .1);
        assertEquals(23, convertedActivity.getElapsedTime());
        assertEquals("2006-04-21T13:20:40Z", convertedActivity.getDate());
        assertEquals("^&UY^&", convertedActivity.getMapPolyline());
        assertEquals("ABC", convertedActivity.getMapSummaryPolyline());

        assertEquals(segmentEfforts, convertedActivity.getSegmentEfforts());
    }

    @Test
    public void convertMultiple() throws Exception {
        List segmentEfforts = asList(mock(SegmentEffort.class));
        doReturn(segmentEfforts).when(segmentEffortConverter).convertList(anyListOf(StravaSegmentEffort.class));

        StravaMap map = new StravaMap("1234", "^&UY^&", "ABC", 5);

        StravaSegment segment = new StravaSegment(2);
        StravaSegmentEffortAthlete athlete = new StravaSegmentEffortAthlete("9");

        StravaActivity dummyActivity = mock(StravaActivity.class);
        StravaSegmentEffort segmentEffort = new StravaSegmentEffort(8, "Pearl Street", athlete, 23.4F, "2006-04-21T13:20:40Z", segment, 15, dummyActivity);

        List<StravaActivity> activities = new ArrayList<>();

        activities.add(new StravaActivity(1, "Pearl Street Climb", "Ride", "Best climb ever", 1.0F, 23, "2006-04-21T13:20:40Z", map, asList(segmentEffort)));

        List<Activity> convertedActivities = activityConverter.convertList(activities);

        assertEquals(1, convertedActivities.size());
        Activity convertedActivity = convertedActivities.get(0);

        assertEquals(1, convertedActivity.getId());
        assertEquals("Pearl Street Climb", convertedActivity.getName());
        assertEquals("Ride", convertedActivity.getType());
        assertEquals("Best climb ever", convertedActivity.getDescription());
        assertEquals(1.0F, convertedActivity.getDistance(), .1);
        assertEquals(23, convertedActivity.getElapsedTime());
        assertEquals("2006-04-21T13:20:40Z", convertedActivity.getDate());
        assertEquals("^&UY^&", convertedActivity.getMapPolyline());
        assertEquals("ABC", convertedActivity.getMapSummaryPolyline());

        assertEquals(segmentEfforts, convertedActivity.getSegmentEfforts());
    }

}