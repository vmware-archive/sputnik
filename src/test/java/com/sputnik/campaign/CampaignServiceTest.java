package com.sputnik.campaign;

import com.sputnik.strava.activity.Activity;
import com.sputnik.strava.activity.ActivityService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CampaignServiceTest extends TestCase {

    @Mock
    ActivityService activityService;

    @Mock
    CampaignRepository campaignRepository;

    @InjectMocks
    CampaignService campaignService;

    @Test
    public void testFindAll() throws Exception {
        List<Campaign> campaigns = asList(mock(Campaign.class));

        doReturn(campaigns).when(campaignRepository).findAll();

        assertEquals(campaigns, campaignService.findAll());
    }

    @Test
    public void testFindForActivityId() throws Exception {
        Activity activity = mock(Activity.class);
        doReturn(activity).when(activityService).getActivityById("17");

        List<Long> segmentIds = asList(123L, 456L);
        doReturn(segmentIds).when(activity).getSegmentIds();

        List<Campaign> campaigns = asList(mock(Campaign.class));
        doReturn(campaigns).when(campaignRepository).findForSegmentIds(segmentIds);

        Iterable<Campaign> returnedCampaigns = campaignService.findForActivityId("17");

        assertEquals(campaigns, returnedCampaigns);
    }

    @Test
    public void testFindForActivityIdNoSegments() throws Exception {
        Activity activity = mock(Activity.class);
        doReturn(activity).when(activityService).getActivityById("17");

        List<Long> segmentIds = new ArrayList<>();
        doReturn(segmentIds).when(activity).getSegmentIds();

        Iterable<Campaign> returnedCampaigns = campaignService.findForActivityId("17");

        List<Long> emptyList = new ArrayList<>();

        verifyZeroInteractions(campaignRepository);
        assertEquals(emptyList, returnedCampaigns);
    }
}