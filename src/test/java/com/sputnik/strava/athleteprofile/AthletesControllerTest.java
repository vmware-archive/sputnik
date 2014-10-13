package com.sputnik.strava.athleteprofile;

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

public class AthletesControllerTest {
    @Mock
    AthleteProfileService athleteProfileService;

    MockMvc mockMvc;

    @InjectMocks
    AthletesController controller;

    @Before
    public void setup() {
        initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testLoad() throws Exception {
        mockMvc.perform(get("/strava/athletes/1234"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetProfile() throws Exception {

        AthleteProfile profile = new AthleteProfile("freddy@example.com", "Fred Derf", "medium.jpg", "large.jpg");

        doReturn(profile).when(athleteProfileService).getAthleteProfileById("1234");

        mockMvc.perform(get("/strava/athletes/1234"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("{\"email\":\"freddy@example.com\",\"name\":\"Fred Derf\",\"avatarMedium\":\"medium.jpg\",\"avatarLarge\":\"large.jpg\"}"));
    }
}
