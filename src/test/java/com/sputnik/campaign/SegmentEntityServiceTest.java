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
}