package com.sputnik.donation;

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

public class DonationsControllerTest{

    @Mock
    DonationService donationService;

    @Mock
    DonationEventService donationEventService;

    @InjectMocks
    DonationsController donationsController;

    MockMvc mockMvc;

    @Before
    public void setup() {
        initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(donationsController).build();
    }

    @Test
    public void testGetDonationsForUserId() throws Exception {
        List<DonationEntity> donations = asList(new DonationEntity(999900L, 2L, 8L, "1234"));
        doReturn(donations).when(donationService).getDonationsForUser(47L);

        Principal principal = mock(Principal.class);
        doReturn("47").when(principal).getName();

        mockMvc.perform(get("/donations").principal(principal))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"amount\":999900,\"donationEvent\":null,\"createdAt\":null}]"));
    }

    @Test
    public void testDonateToCampaign() throws Exception {
        DonationEvent donationEvent= new DonationEvent(12L, 45L, 56L, "WERTYH");
        DonationResponse donationResponse = new DonationResponse(100, donationEvent, null);

        doReturn(donationResponse).when(donationService).create(any(PendingDonation.class));

        Principal principal = mock(Principal.class);
        doReturn("47").when(principal).getName();

        mockMvc.perform(post("/donationEvents/14/donation")
                .content("{\"amount\": \"100\", \"token\": \"123TOKEN123\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .principal(principal))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("{\"amount\":100,\"donationEvent\":{\"id\":0,\"athleteId\":12,\"campaignId\":45,\"activityId\":56,\"polyline\":\"WERTYH\",\"campaign\":null},\"createdAt\":null}"));

        ArgumentCaptor<PendingDonation> pendingDonationCaptor = ArgumentCaptor.forClass(PendingDonation.class);
        verify(donationService).create(pendingDonationCaptor.capture());

        PendingDonation capturedPendingDonation = pendingDonationCaptor.getValue();

        assertEquals(100, capturedPendingDonation.getAmount());
        assertEquals("123TOKEN123", capturedPendingDonation.getToken());
        assertEquals(14, capturedPendingDonation.getDonationEventId());
        assertEquals(47, capturedPendingDonation.getUserId());
    }

    @Test
    public void testDonateToCampaignFailure() throws Exception {
        doReturn(null).when(donationService).create(any(PendingDonation.class));

        Principal principal = mock(Principal.class);
        doReturn("47").when(principal).getName();

        mockMvc.perform(post("/donationEvents/14/donation")
                .content("{\"amount\": \"100\", \"token\": \"123TOKEN123\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .principal(principal))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testFindOrCreateDonationEvent() throws Exception {
        DonationEvent donationEvent = new DonationEvent(13L, 34L, 57L, "ASDFGH");
        doReturn(donationEvent).when(donationEventService).findOrCreate(any(DonationEvent.class));

        Principal principal = mock(Principal.class);
        doReturn("12").when(principal).getName();

        mockMvc.perform(post("/donationEvents")
                .content("{\"campaignId\":45,\"activityId\":56,\"polyline\":\"WERTYH\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .principal(principal))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":0,\"athleteId\":13,\"campaignId\":34,\"activityId\":57,\"polyline\":\"ASDFGH\",\"campaign\":null}"));

        ArgumentCaptor<DonationEvent> donationEventCaptor = ArgumentCaptor.forClass(DonationEvent.class);
        verify(donationEventService).findOrCreate(donationEventCaptor.capture());

        DonationEvent capturedDonationEvent = donationEventCaptor.getValue();

        assertEquals(12L, capturedDonationEvent.getAthleteId());
        assertEquals(45L, capturedDonationEvent.getCampaignId());
        assertEquals(56L, capturedDonationEvent.getActivityId());
        assertEquals("WERTYH", capturedDonationEvent.getPolyline());
    }

    @Test
    public void testGetDonationEvent() throws Exception {
        DonationEvent donationEvent = new DonationEvent(13L, 34L, 57L, "ASDFGH");
        doReturn(donationEvent).when(donationEventService).findById(48L);

        mockMvc.perform(get("/donationEvents/48"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":0,\"athleteId\":13,\"campaignId\":34,\"activityId\":57,\"polyline\":\"ASDFGH\",\"campaign\":null}"));

    }
}