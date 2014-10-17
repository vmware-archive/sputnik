package com.sputnik.admin;

import com.sputnik.campaign.Campaign;
import com.sputnik.campaign.CampaignService;
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
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminCampaignsControllerTest {
    @Mock
    CampaignService campaignService;

    MockMvc mockMvc;

    @InjectMocks
    AdminCampaignsController controller;

    @Before
    public void setup() {
        initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testLoad() throws Exception {
        mockMvc.perform(get("/admin/campaigns"))
                .andExpect(status().isOk());
    }

    @Test
    public void testListCampaigns() throws Exception {
        List<Campaign> allCampaigns = asList(new Campaign("Lyons", "Flood recovery"));

        doReturn(allCampaigns).when(campaignService).findAll();

        mockMvc.perform(get("/admin/campaigns"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("[{\"id\":0,\"title\":\"Lyons\",\"description\":\"Flood recovery\"}]"));
    }

    @Test
    public void testCreateCampaign() throws Exception {
        Campaign campaign = new Campaign("Boulder", "Flood damage");

        doReturn(campaign).when(campaignService).create(any(Campaign.class));

        mockMvc.perform(post("/admin/campaigns")
                .content("{\"title\": \"Longmont\", \"description\": \"Damage\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("{\"id\":0,\"title\":\"Boulder\",\"description\":\"Flood damage\"}"));

        ArgumentCaptor<Campaign> campaignCaptor = ArgumentCaptor.forClass(Campaign.class);
        verify(campaignService).create(campaignCaptor.capture());

        Campaign capturedCampaign = campaignCaptor.getValue();

        assertEquals("Longmont", capturedCampaign.getTitle());
        assertEquals("Damage", capturedCampaign.getDescription());
    }

    @Test
    public void testUpdateCampaign() throws Exception {
        Campaign campaign = new Campaign("Boulder", "Flood damage");

        doReturn(campaign).when(campaignService).update(anyLong(), any(Campaign.class));

        mockMvc.perform(patch("/admin/campaigns/7")
                .content("{\"title\": \"Longmont\", \"description\": \"Damage\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("{\"id\":0,\"title\":\"Boulder\",\"description\":\"Flood damage\"}"));

        ArgumentCaptor<Campaign> campaignCaptor = ArgumentCaptor.forClass(Campaign.class);
        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
        verify(campaignService).update(idCaptor.capture(), campaignCaptor.capture());

        Campaign capturedCampaign = campaignCaptor.getValue();
        Long capturedId = idCaptor.getValue();

        assertEquals(new Long(7), capturedId);
        assertEquals("Longmont", capturedCampaign.getTitle());
        assertEquals("Damage", capturedCampaign.getDescription());
    }
}