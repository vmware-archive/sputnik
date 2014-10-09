package com.sputnik.scheduled;

import com.sputnik.notification.NotificationService;
import com.sputnik.strava.StravaService;
import com.sputnik.strava.segmenteffort.SegmentEffort;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Mock
    ProviderUserEmailRepository providerUserEmailRepository;
    @Mock
    NotificationService notificationService;
    @Mock
    List<SegmentEffort> allSegmentEfforts;

    @InjectMocks
    SponsoredSegmentFinder sponsoredSegmentFinder;

    @Test
    public void testRetrieve() throws Exception {
        Map<String, String> userInfoMap = new HashMap<>();

        doReturn(stravaService).when(systemConnection).getStravaService();
        doReturn("start").when(segmentEffortTimeframe).getStartTime();
        doReturn("end").when(segmentEffortTimeframe).getEndTime();

        doReturn(allSegmentEfforts).when(stravaService).getAllSegmentEfforts("start", "end");
        doReturn(userInfoMap).when(providerUserEmailRepository).findAll();

        sponsoredSegmentFinder.retrieve();

        verify(notificationService).notifyUsers(userInfoMap, allSegmentEfforts);
    }
}