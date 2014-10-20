package com.sputnik.campaign;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class SegmentEntityTest {
    @Test
    public void testAddCampaign() throws Exception {
        Campaign campaign1 = mock(Campaign.class);
        Campaign campaign2 = mock(Campaign.class);

        SegmentEntity segmentEntity = new SegmentEntity(123L);

        segmentEntity.addCampaign(campaign1);

        assertEquals(asList(campaign1), segmentEntity.getCampaigns());

        segmentEntity.addCampaign(campaign2);

        assertEquals(asList(campaign1, campaign2), segmentEntity.getCampaigns());
    }
    @Test
    public void testRemoveCampaign() throws Exception {
        Campaign campaign1 = mock(Campaign.class);
        Campaign campaign2 = mock(Campaign.class);

        doReturn(1L).when(campaign1).getId();
        doReturn(2L).when(campaign2).getId();

        SegmentEntity segmentEntity = new SegmentEntity(123L);
        segmentEntity.addCampaign(campaign1);
        segmentEntity.addCampaign(campaign2);

        segmentEntity.removeCampaign(1L);

        assertEquals(asList(campaign2), segmentEntity.getCampaigns());
    }
}