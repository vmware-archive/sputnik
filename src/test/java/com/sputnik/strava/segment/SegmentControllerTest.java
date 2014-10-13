package com.sputnik.strava.segment;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SegmentControllerTest {
    @Mock
    SegmentService segmentService;

    MockMvc mockMvc;

    @InjectMocks
    SegmentController controller;

    @Before
    public void setup() {
        initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testLoad() throws Exception {
        mockMvc.perform(get("/strava/segments/1234567"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetSegment() throws Exception {
        Segment segment = new Segment(4, "Pearl Street", "foosball", 123.4F, "}{POIU()P{L:");

        doReturn(segment).when(segmentService).getSegmentById("1234567");

        mockMvc.perform(get("/strava/segments/1234567"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("{\"id\":4,\"name\":\"Pearl Street\",\"activityType\":\"foosball\",\"distance\":123.4,\"mapPolyline\":\"}{POIU()P{L:\"}"));
    }
}
