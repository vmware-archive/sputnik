package com.sputnik.campaigns;

import com.sputnik.campaign.Campaign;
import com.sputnik.campaign.CampaignService;
import com.sputnik.campaign.CampaignsController;
import com.sputnik.donation.DonationResponse;
import com.sputnik.donation.DonationService;
import com.sputnik.donation.PendingDonation;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CampaignsControllerTest {
    @Mock
    CampaignService campaignService;

    @Mock
    DonationService donationService;

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

    @Test
    public void testGetCampaign() throws Exception {
        Campaign campaign = new Campaign("Lyons", "Flood recovery");

        doReturn(campaign).when(campaignService).findById(8L);

        mockMvc.perform(get("/campaigns/8"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("{\"id\":0,\"title\":\"Lyons\",\"description\":\"Flood recovery\"}"));

        verify(campaignService).findById(8L);
    }

    @Test
    public void testDonateToCampaign() throws Exception {
        Campaign campaign = new Campaign("Lyons", "Flood recovery");

        DonationResponse donationResponse = new DonationResponse(100, campaign, null);

        doReturn(donationResponse).when(donationService).create(any(PendingDonation.class));

        Principal principal = mock(Principal.class);
        doReturn("47").when(principal).getName();

        mockMvc.perform(post("/campaigns/8/donate")
                .content("{\"amount\": \"100\", \"cardNumber\": \"4242424242424242\", \"cardExpirationMonth\": \"08\", \"cardExpirationYear\": \"2015\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .principal(principal))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("{\"amount\":100,\"campaign\":{\"id\":0,\"title\":\"Lyons\",\"description\":\"Flood recovery\"},\"createdAt\":null}"));

        ArgumentCaptor<PendingDonation> pendingDonationCaptor = ArgumentCaptor.forClass(PendingDonation.class);
        verify(donationService).create(pendingDonationCaptor.capture());

        PendingDonation capturedPendingDonation = pendingDonationCaptor.getValue();

        assertEquals(100, capturedPendingDonation.getAmount());
        assertEquals("4242424242424242", capturedPendingDonation.getCardNumber());
        assertEquals("08", capturedPendingDonation.getCardExpirationMonth());
        assertEquals("2015", capturedPendingDonation.getCardExpirationYear());
        assertEquals(8, capturedPendingDonation.getCampaignId());
        assertEquals(47, capturedPendingDonation.getUserId());
    }

    @Test
    public void testDonateToCampaignFailure() throws Exception {
        doReturn(null).when(donationService).create(any(PendingDonation.class));

        Principal principal = mock(Principal.class);
        doReturn("47").when(principal).getName();

        mockMvc.perform(post("/campaigns/8/donate")
                .content("{\"amount\": \"100\", \"cardNumber\": \"4242424242424242\", \"cardExpirationMonth\": \"08\", \"cardExpirationYear\": \"2015\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .principal(principal))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetDonationTotal() throws Exception {
        doReturn(100L).when(donationService).getDonationTotal(1L);

        mockMvc.perform(get("/campaigns/1/donate"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("100"));
    }

}
