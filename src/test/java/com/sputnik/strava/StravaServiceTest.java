package com.sputnik.strava;

import com.sputnik.strava.activity.ActivityConverter;
import com.sputnik.strava.profile.AthleteProfile;
import com.sputnik.strava.profile.AthleteProfileConverter;
import com.sputnik.strava.segment.Segment;
import com.sputnik.strava.segment.SegmentConverter;
import com.sputnik.strava.segmenteffort.SegmentEffort;
import com.sputnik.strava.segmenteffort.SegmentEffortConverter;
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
    ActivityOperations activityOperations;

    @Mock
    ActivityConverter activityConverter;

    @Mock
    AthleteProfileConverter athleteProfileConverter;

    @Mock
    SegmentConverter segmentConverter;

    @Mock
    SegmentEffortConverter segmentEffortConverter;

    @InjectMocks
    StravaService stravaService;

    @Test
    public void testGetSegmentEfforts() throws Exception {
        String[] segmentIds = {"8269800", "4784391"};
        stravaService.setSegmentIds(segmentIds);

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

        List<SegmentEffort> returnedSegmentEfforts = stravaService.getSegmentEfforts();

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


        SegmentEffort returnedSegmentEffort = stravaService.getSegmentEffortById("1234567");

        assertEquals(segmentEffort, returnedSegmentEffort);
    }

    @Test
    public void testGetAllSegmentEfforts() throws Exception {
        String[] segmentIds = {"8269800", "4784391"};
        stravaService.setSegmentIds(segmentIds);

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

        List<SegmentEffort> returnedSegmentEfforts = stravaService.getAllSegmentEfforts("2006-04-21T13:20:40Z", "2006-04-22T13:20:40Z");

        assertEquals(segmentEfforts, returnedSegmentEfforts);

        verify(segmentEffortOperations).getAllSegmentEfforts("8269800", "2006-04-21T13:20:40Z", "2006-04-22T13:20:40Z");
        verify(segmentEffortOperations).getAllSegmentEfforts("4784391", "2006-04-21T13:20:40Z", "2006-04-22T13:20:40Z");
    }

    @Test
    public void testGetAthleteProfile() throws Exception {
        StravaAthleteProfile stravaAthleteProfile = mock(StravaAthleteProfile.class);
        AthleteProfile athleteProfile = mock(AthleteProfile.class);

        doReturn(athleteOperations).when(strava).athleteOperations();
        doReturn(stravaAthleteProfile).when(athleteOperations).getAthleteProfile();
        doReturn(athleteProfile).when(athleteProfileConverter).convert(stravaAthleteProfile);

        AthleteProfile returnedAthleteProfile = stravaService.getAthleteProfile();

        assertEquals(athleteProfile, returnedAthleteProfile);
    }

    @Test
    public void testGetAthleteProfileById() throws Exception {
        StravaAthleteProfile stravaAthleteProfile = mock(StravaAthleteProfile.class);
        AthleteProfile athleteProfile = mock(AthleteProfile.class);

        doReturn(athleteOperations).when(strava).athleteOperations();
        doReturn(stravaAthleteProfile).when(athleteOperations).getAthleteProfileById("17");
        doReturn(athleteProfile).when(athleteProfileConverter).convert(stravaAthleteProfile);

        AthleteProfile returnedAthleteProfile = stravaService.getAthleteProfileById("17");

        assertEquals(athleteProfile, returnedAthleteProfile);
    }

    @Test
    public void testGetSegmentById() throws Exception {
        Segment segment = mock(Segment.class);
        StravaSegment stravaSegment = mock(StravaSegment.class);

        doReturn(segmentOperations).when(strava).segmentOperations();
        doReturn(stravaSegment).when(segmentOperations).getSegmentById("1234567");
        doReturn(segment).when(segmentConverter).convert(stravaSegment);

        Segment returnedSegment = stravaService.getSegmentById("1234567");

        assertEquals(segment, returnedSegment);
    }
}