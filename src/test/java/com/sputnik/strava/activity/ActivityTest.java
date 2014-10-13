package com.sputnik.strava.activity;

import com.sputnik.strava.segmenteffort.SegmentEffort;
import junit.framework.TestCase;

import java.util.List;

import static java.util.Arrays.asList;

public class ActivityTest extends TestCase {

    public void testGetSegmentIds() throws Exception {
        SegmentEffort firstSegmentEffort = new SegmentEffort(1, "Pearl Street", "34", 34F, "Tuesday", 123, 5L);
        SegmentEffort secondSegmentEffort = new SegmentEffort(1, "Pearl Street", "34", 34F, "Tuesday", 456, 5L);
        SegmentEffort thirdSegmentEffort = new SegmentEffort(1, "Pearl Street", "34", 34F, "Tuesday", 789, 5L);

        List<SegmentEffort> segmentEfforts = asList(firstSegmentEffort, secondSegmentEffort, thirdSegmentEffort);

        Activity activity = new Activity(123, "Pearl Street", "Ride", "Cool ride", 23.4F, 15, "2006-04-21T13:20:40Z", "^&UIHT^&", "(*&",  segmentEfforts);

        assertEquals(asList(123L, 456L, 789L), activity.getSegmentIds());
    }
}