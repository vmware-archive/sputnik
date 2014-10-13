package com.sputnik.campaigns;

import com.sputnik.campaign.Campaign;
import com.sputnik.campaign.CampaignService;
import com.sputnik.campaign.CampaignsController;
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

public class CampaignsControllerTest {
    @Mock
    CampaignService campaignService;

    MockMvc mockMvc;

    @InjectMocks
    CampaignsController controller;

    @Before
    public void setup() {
        initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testLoad() throws Exception {
        mockMvc.perform(get("/campaigns"))
                .andExpect(status().isOk());
    }

    @Test
    public void testListCampaigns() throws Exception {
        List<Campaign> allCampaigns = asList(new Campaign("Lyons", "Flood recovery"));

        doReturn(allCampaigns).when(campaignService).findAll();

        mockMvc.perform(get("/campaigns"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("[{\"id\":0,\"title\":\"Lyons\",\"description\":\"Flood recovery\"}]"));
    }

    @Test
    public void testListCampaignsForActivity() throws Exception {
        List<Campaign> allCampaigns = asList(new Campaign("Lyons", "Flood recovery"));

        doReturn(allCampaigns).when(campaignService).findForActivityId("4");

        mockMvc.perform(get("/campaigns?activityId=4"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("[{\"id\":0,\"title\":\"Lyons\",\"description\":\"Flood recovery\"}]"));
    }
}
