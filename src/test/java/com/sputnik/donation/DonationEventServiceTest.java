package com.sputnik.donation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DonationEventServiceTest {

    @Mock
    DonationEventRepository donationEventRepository;

    @InjectMocks
    DonationEventService donationEventService;

    @Test
    public void testFindOrCreateFind() throws Exception {
        DonationEvent donationEvent = mock(DonationEvent.class);

        doReturn(donationEvent).when(donationEventRepository).findByCampaignIdAndActivityId(56L, 89L);

        DonationEvent result = donationEventService.findOrCreate(new DonationEvent(12L, 56L, 89L, "123456SDFGHJ"));

        assertEquals(donationEvent, result);
    }

    @Test
    public void testFindOrCreateCreate() throws Exception {
        DonationEvent donationEvent = mock(DonationEvent.class);

        doReturn(null).when(donationEventRepository).findByCampaignIdAndActivityId(56L, 89L);
        doReturn(donationEvent).when(donationEventRepository).save(any(DonationEvent.class));

        DonationEvent result = donationEventService.findOrCreate(new DonationEvent(12L, 56L, 89L, "123456SDFGHJ"));

        assertEquals(donationEvent, result);

        ArgumentCaptor<DonationEvent> donationEventCaptor = ArgumentCaptor.forClass(DonationEvent.class);
        verify(donationEventRepository).save(donationEventCaptor.capture());

        DonationEvent capturedDonationEvent = donationEventCaptor.getValue();

        assertEquals(12L, capturedDonationEvent.getAthleteId());
        assertEquals(56L, capturedDonationEvent.getCampaignId());
        assertEquals(89L, capturedDonationEvent.getActivityId());
        assertEquals("123456SDFGHJ", capturedDonationEvent.getPolyline());
    }

    @Test
    public void testFindById() throws Exception {
        DonationEvent donationEvent = mock(DonationEvent.class);

        doReturn(donationEvent).when(donationEventRepository).findOne(18L);

        DonationEvent result = donationEventService.findById(18L);

        assertEquals(donationEvent, result);
    }
}