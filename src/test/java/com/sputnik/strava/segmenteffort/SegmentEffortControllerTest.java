package com.sputnik.strava.segmenteffort;

import com.sputnik.strava.StravaService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SegmentEffortControllerTest {
    @Mock
    StravaService stravaService;

    MockMvc mockMvc;

    @InjectMocks
    SegmentEffortController controller;

    @Before
    public void setup() {
        initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testLoad() throws Exception {
        mockMvc.perform(get("/strava/segment_efforts"))
                .andExpect(status().isOk());
    }

    @Test
    public void testListSegmentEfforts() throws Exception {
        List<SegmentEffort> allSegmentEfforts = asList(new SegmentEffort(123, "Pearl Street", 456, 2.3F, "2006-04-21T13:20:40Z", 789, 10));

        doReturn(allSegmentEfforts).when(stravaService).getSegmentEfforts();

        mockMvc.perform(get("/strava/segment_efforts"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("[{\"id\":123,\"name\":\"Pearl Street\",\"athleteId\":456,\"distance\":2.3,\"date\":\"2006-04-21T13:20:40Z\",\"segmentId\":789,\"elapsedTime\":10}]"));
    }

    @Test
    public void testGetSegmentEffort() throws Exception {
        SegmentEffort segmentEffort = new SegmentEffort(123, "Pearl Street", 456, 2.3F, "2006-04-21T13:20:40Z", 789, 10);

        doReturn(segmentEffort).when(stravaService).getSegmentEffortById("1234567");

        mockMvc.perform(get("/strava/segment_efforts/1234567"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("{\"id\":123,\"name\":\"Pearl Street\",\"athleteId\":456,\"distance\":2.3,\"date\":\"2006-04-21T13:20:40Z\",\"segmentId\":789,\"elapsedTime\":10}"));
    }
}
