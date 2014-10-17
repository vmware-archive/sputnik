package com.sputnik.admin;

import com.sputnik.campaign.SegmentEntity;
import com.sputnik.campaign.SegmentEntityService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminSegmentsControllerTest {
    @Mock
    SegmentEntityService segmentService;

    MockMvc mockMvc;

    @InjectMocks
    AdminSegmentsController controller;

    @Before
    public void setup() {
        initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testLoad() throws Exception {
        mockMvc.perform(get("/admin/segments"))
                .andExpect(status().isOk());
    }

    @Test
    public void testListSegments() throws Exception {
        List<SegmentEntity> allSegments = asList(new SegmentEntity(123456));

        doReturn(allSegments).when(segmentService).findAll();

        mockMvc.perform(get("/admin/segments"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("[{\"id\":0,\"remoteid\":123456}]"));
    }

    @Test
    public void testCreateSegment() throws Exception {
        SegmentEntity segment = new SegmentEntity(123456);

        doReturn(segment).when(segmentService).create(any(SegmentEntity.class));

        mockMvc.perform(post("/admin/segments")
                .content("{\"remoteid\": 7890}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("{\"id\":0,\"remoteid\":123456}"));

        ArgumentCaptor<SegmentEntity> segmentCaptor = ArgumentCaptor.forClass(SegmentEntity.class);
        verify(segmentService).create(segmentCaptor.capture());

        SegmentEntity capturedSegment = segmentCaptor.getValue();

        assertEquals(7890, capturedSegment.getRemoteid());
    }

    @Test
    public void testDeleteSegment() throws Exception {
        mockMvc.perform(delete("/admin/segments/7"))
                .andExpect(status().isOk());

        verify(segmentService).delete(7L);
    }

}