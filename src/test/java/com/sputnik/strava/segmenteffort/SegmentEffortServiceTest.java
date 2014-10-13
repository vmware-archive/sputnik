package com.sputnik.strava.segmenteffort;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.social.strava.api.*;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SegmentEffortServiceTest extends TestCase {
    @Mock
    Strava strava;

    @Mock
    AthleteOperations athleteOperations;

    @Mock
    SegmentEffortOperations segmentEffortOperations;

    @Mock
    SegmentEffortConverter segmentEffortConverter;

    @InjectMocks
    SegmentEffortService segmentEffortService;

    @Test
    public void testGetSegmentEfforts() throws Exception {
        String[] segmentIds = {"8269800", "4784391"};
        segmentEffortService.setSegmentIds(segmentIds);

        StravaAthleteProfile stravaAthleteProfile = mock(StravaAthleteProfile.class);

        doReturn(athleteOperations).when(strava).athleteOperations();
        doReturn(stravaAthleteProfile).when(athleteOperations).getAthleteProfile();
        doReturn(567L).when(stravaAthleteProfile).getId();

        List<StravaSegmentEffort> stravaSegmentEfforts = asList(mock(StravaSegmentEffort.class));
        List<StravaSegmentEffort> moreStravaSegmentEfforts = asList(mock(StravaSegmentEffort.class));

        List<SegmentEffort> segmentEfforts = asList(mock(SegmentEffort.class));

        doReturn(segmentEffortOperations).when(strava).segmentEffortOperations();

        doReturn(stravaSegmentEfforts).when(segmentEffortOperations).getSegmentEfforts("8269800", "567");
        doReturn(moreStravaSegmentEfforts).when(segmentEffortOperations).getSegmentEfforts("4784391", "567");
        doReturn(segmentEfforts).when(segmentEffortConverter).convertList(anyListOf(StravaSegmentEffort.class));

        List<SegmentEffort> returnedSegmentEfforts = segmentEffortService.getSegmentEfforts();

        assertEquals(segmentEfforts, returnedSegmentEfforts);

        verify(segmentEffortOperations).getSegmentEfforts("8269800", "567");
        verify(segmentEffortOperations).getSegmentEfforts("4784391", "567");
    }

    @Test
    public void testGetSegmentEffortById() throws Exception {
        SegmentEffort segmentEffort = mock(SegmentEffort.class);
        StravaSegmentEffort stravaSegmentEffort = mock(StravaSegmentEffort.class);

        doReturn(segmentEffortOperations).when(strava).segmentEffortOperations();
        doReturn(stravaSegmentEffort).when(segmentEffortOperations).getSegmentEffortById("1234567");
        doReturn(segmentEffort).when(segmentEffortConverter).convert(stravaSegmentEffort);


        SegmentEffort returnedSegmentEffort = segmentEffortService.getSegmentEffortById("1234567");

        assertEquals(segmentEffort, returnedSegmentEffort);
    }

    @Test
    public void testGetAllSegmentEfforts() throws Exception {
        String[] segmentIds = {"8269800", "4784391"};
        segmentEffortService.setSegmentIds(segmentIds);

        StravaAthleteProfile stravaAthleteProfile = mock(StravaAthleteProfile.class);

        doReturn(athleteOperations).when(strava).athleteOperations();
        doReturn(stravaAthleteProfile).when(athleteOperations).getAthleteProfile();
        doReturn(567L).when(stravaAthleteProfile).getId();

        List<StravaSegmentEffort> stravaSegmentEfforts = asList(mock(StravaSegmentEffort.class));
        List<StravaSegmentEffort> moreStravaSegmentEfforts = asList(mock(StravaSegmentEffort.class));

        List<SegmentEffort> segmentEfforts = asList(mock(SegmentEffort.class));

        doReturn(segmentEffortOperations).when(strava).segmentEffortOperations();

        doReturn(stravaSegmentEfforts).when(segmentEffortOperations).getAllSegmentEfforts("8269800", "2006-04-21T13:20:40Z", "2006-04-22T13:20:40Z");
        doReturn(moreStravaSegmentEfforts).when(segmentEffortOperations).getAllSegmentEfforts("4784391", "2006-04-21T13:20:40Z", "2006-04-22T13:20:40Z");
        doReturn(segmentEfforts).when(segmentEffortConverter).convertList(anyListOf(StravaSegmentEffort.class));

        List<SegmentEffort> returnedSegmentEfforts = segmentEffortService.getAllSegmentEfforts("2006-04-21T13:20:40Z", "2006-04-22T13:20:40Z");

        assertEquals(segmentEfforts, returnedSegmentEfforts);

        verify(segmentEffortOperations).getAllSegmentEfforts("8269800", "2006-04-21T13:20:40Z", "2006-04-22T13:20:40Z");
        verify(segmentEffortOperations).getAllSegmentEfforts("4784391", "2006-04-21T13:20:40Z", "2006-04-22T13:20:40Z");
    }
}