package com.sputnik.strava.segmenteffort;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.social.strava.api.StravaSegment;
import org.springframework.social.strava.api.StravaSegmentEffort;
import org.springframework.social.strava.api.StravaSegmentEffortAthlete;

@RunWith(MockitoJUnitRunner.class)
public class SegmentEffortConverterTest extends TestCase {
    @InjectMocks
    SegmentEffortConverter segmentEffortConverter;

    @Test
    public void testConvert() throws Exception {
        StravaSegment stravaSegment = new StravaSegment(2);
        StravaSegmentEffortAthlete stravaAthlete = new StravaSegmentEffortAthlete("9");
        StravaSegmentEffort segmentEffort = new StravaSegmentEffort(8, "Pearl Street", stravaAthlete, 23.4F, "2006-04-21T13:20:40Z", stravaSegment, 15);

        SegmentEffort convertedSegmentEffort = segmentEffortConverter.convert(segmentEffort);

        assertEquals(8, convertedSegmentEffort.getId());
        assertEquals("Pearl Street", convertedSegmentEffort.getName());
        assertEquals("9", convertedSegmentEffort.getAthleteId());
        assertEquals(23.4, convertedSegmentEffort.getDistance(), .1);
        assertEquals("2006-04-21T13:20:40Z", convertedSegmentEffort.getDate());
        assertEquals(2, convertedSegmentEffort.getSegmentId());
        assertEquals(15, convertedSegmentEffort.getElapsedTime());
    }
}