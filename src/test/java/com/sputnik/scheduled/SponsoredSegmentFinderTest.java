package com.sputnik.scheduled;

import com.sputnik.strava.StravaService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SponsoredSegmentFinderTest extends TestCase {

    @Mock
    StravaService stravaService;

    @Mock
    SystemConnection systemConnection;

    @Mock
    SegmentEffortTimeframe segmentEffortTimeframe;

    @InjectMocks
    SponsoredSegmentFinder sponsoredSegmentFinder;

    @Test
    public void testRetrieve() throws Exception {
        doReturn(stravaService).when(systemConnection).getStravaService();
        doReturn("start").when(segmentEffortTimeframe).getStartTime();
        doReturn("end").when(segmentEffortTimeframe).getEndTime();

        sponsoredSegmentFinder.retrieve();

        verify(stravaService).getAllSegmentEfforts("start", "end");
    }
}