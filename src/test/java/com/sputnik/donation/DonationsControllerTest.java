package com.sputnik.donation;

import org.junit.Before;import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DonationsControllerTest{

    @Mock
    DonationService donationService;

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
                .andExpect(content().string("[{\"amount\":999900,\"campaign\":null,\"createdAt\":null}]"));
    }
}