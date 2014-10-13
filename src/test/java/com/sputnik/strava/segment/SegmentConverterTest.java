package com.sputnik.strava.segment;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.social.strava.api.StravaMap;
import org.springframework.social.strava.api.StravaSegment;

@RunWith(MockitoJUnitRunner.class)
public class SegmentConverterTest extends TestCase {

    @InjectMocks
    SegmentConverter segmentConverter;

    @Test
    public void testConvert() throws Exception {
        StravaMap map = new StravaMap("1234", "^&UY^&", "ABC", 5);
        StravaSegment segment = new StravaSegment(2, "Pearl Street", "foosball", 123.4F, map);

        Segment convertedSegment = segmentConverter.convert(segment);

        assertEquals(2, convertedSegment.getId());
        assertEquals("Pearl Street", convertedSegment.getName());
        assertEquals("foosball", convertedSegment.getActivityType());
        assertEquals(123.4, convertedSegment.getDistance(), .1);
        assertEquals("^&UY^&", convertedSegment.getMapPolyline());
    }
}