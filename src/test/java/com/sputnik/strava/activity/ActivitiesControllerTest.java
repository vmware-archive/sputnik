package com.sputnik.strava.activity;

import com.sputnik.strava.segmenteffort.SegmentEffort;
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

public class ActivitiesControllerTest {
    @Mock
    ActivityService activityService;

    MockMvc mockMvc;

    @InjectMocks
    ActivitiesController controller;

    @Before
    public void setup() {
        initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testLoad() throws Exception {
        mockMvc.perform(get("/strava/activities"))
                .andExpect(status().isOk());
    }

    @Test
    public void testListActivities() throws Exception {
        List<SegmentEffort> segmentEfforts = asList(new SegmentEffort(123, "Pearl Street", "456", 2.3F, "2006-04-21T13:20:40Z", 789, 10, 4));
        List<Activity> activities = asList(new Activity(123, "Pearl Street", "Ride", "Cool ride", 23.4F, 15, "2006-04-21T13:20:40Z", "^&UIHT^&", "(*&",  segmentEfforts));

        doReturn(activities).when(activityService).getActivities();

        mockMvc.perform(get("/strava/activities"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("[{\"id\":123,\"name\":\"Pearl Street\",\"type\":\"Ride\",\"description\":\"Cool ride\",\"distance\":23.4,\"elapsedTime\":15,\"date\":\"2006-04-21T13:20:40Z\",\"mapPolyline\":\"^&UIHT^&\",\"mapSummaryPolyline\":\"(*&\",\"segmentEfforts\":" +
                        "[{\"id\":123,\"name\":\"Pearl Street\",\"athleteId\":\"456\",\"distance\":2.3,\"date\":\"2006-04-21T13:20:40Z\",\"segmentId\":789,\"elapsedTime\":10,\"activityId\":4}]" +
                        "}]"));
    }

    @Test
    public void testGetActivity() throws Exception {
        List<SegmentEffort> segmentEfforts = asList(new SegmentEffort(123, "Pearl Street", "456", 2.3F, "2006-04-21T13:20:40Z", 789, 10, 4));
        Activity activity = new Activity(123, "Pearl Street", "Ride", "Cool ride", 23.4F, 15, "2006-04-21T13:20:40Z", "^&UIHT^&", "(*&", segmentEfforts);

        doReturn(activity).when(activityService).getActivityById("7");

        mockMvc.perform(get("/strava/activities/7"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("{\"id\":123,\"name\":\"Pearl Street\",\"type\":\"Ride\",\"description\":\"Cool ride\",\"distance\":23.4,\"elapsedTime\":15,\"date\":\"2006-04-21T13:20:40Z\",\"mapPolyline\":\"^&UIHT^&\",\"mapSummaryPolyline\":\"(*&\",\"segmentEfforts\":" +
                        "[{\"id\":123,\"name\":\"Pearl Street\",\"athleteId\":\"456\",\"distance\":2.3,\"date\":\"2006-04-21T13:20:40Z\",\"segmentId\":789,\"elapsedTime\":10,\"activityId\":4}]" +
                        "}"));
    }

}
