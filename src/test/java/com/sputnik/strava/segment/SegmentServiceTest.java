package com.sputnik.strava.segment;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.social.strava.api.SegmentOperations;
import org.springframework.social.strava.api.Strava;
import org.springframework.social.strava.api.StravaSegment;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class SegmentServiceTest extends TestCase {
    @Mock
    Strava strava;

    @Mock
    SegmentOperations segmentOperations;

    @Mock
    SegmentConverter segmentConverter;

    @InjectMocks
    SegmentService segmentService;

    @Test
    public void testGetSegmentById() throws Exception {
        Segment segment = mock(Segment.class);
        StravaSegment stravaSegment = mock(StravaSegment.class);

        doReturn(segmentOperations).when(strava).segmentOperations();
        doReturn(stravaSegment).when(segmentOperations).getSegmentById("1234567");
        doReturn(segment).when(segmentConverter).convert(stravaSegment);

        Segment returnedSegment = segmentService.getSegmentById("1234567");

        assertEquals(segment, returnedSegment);
    }
}