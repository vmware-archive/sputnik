package com.sputnik.campaign;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SegmentEntityServiceTest extends TestCase {

    @Mock
    SegmentRepository segmentRepository;

    @Mock
    CampaignRepository campaignRepository;

    @InjectMocks
    SegmentEntityService segmentEntityService;

    @Test
    public void testFindAll() throws Exception {
        List<SegmentEntity> segmentEntities = asList(mock(SegmentEntity.class));

        doReturn(segmentEntities).when(segmentRepository).findAll();

        assertEquals(segmentEntities, segmentEntityService.findAll());
    }

    @Test
    public void testCreate() throws Exception {
        SegmentEntity segmentEntity = mock(SegmentEntity.class);
        SegmentEntity returnedSegmentEntity = mock(SegmentEntity.class);
        doReturn(returnedSegmentEntity).when(segmentRepository).save(segmentEntity);

        assertEquals(returnedSegmentEntity, segmentEntityService.create(segmentEntity));
    }

    @Test
    public void testDelete() throws Exception {
        segmentEntityService.delete(7L);

        verify(segmentRepository).delete(7L);
    }

    @Test
    public void testAddCampaign() throws Exception {
        SegmentEntity segmentEntity = mock(SegmentEntity.class);
        Campaign campaign = mock(Campaign.class);

        doReturn(segmentEntity).when(segmentRepository).findOne(3L);
        doReturn(campaign).when(campaignRepository).findOne(15L);

        segmentEntityService.addCampaign(3L, 15L);

        verify(segmentEntity).addCampaign(campaign);
        verify(segmentRepository).save(segmentEntity);
    }

    @Test
    public void testRemoveCampaign() throws Exception {
        SegmentEntity segmentEntity = mock(SegmentEntity.class);
        doReturn(segmentEntity).when(segmentRepository).findOne(3L);

        segmentEntityService.removeCampaign(3L, 15L);

        verify(segmentEntity).removeCampaign(15L);
        verify(segmentRepository).save(segmentEntity);
    }
}