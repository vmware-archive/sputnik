package com.sputnik.donation;

import com.stripe.model.Charge;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.Arrays.asList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DonationServiceTest extends TestCase {

    @Mock
    DonationRepository donationRepository;

    @Mock
    StripeService stripeService;

    @InjectMocks
    DonationService donationService;

    @Test
    public void testCreate() throws Exception {
        Charge charge = mock(Charge.class);
        DonationEntity donation = new DonationEntity(234, 87L, 45L, "REMOTE-ID-123");

        doReturn(123).when(charge).getAmount();
        doReturn("REMOTE-ID-123").when(charge).getId();
        doReturn(donation).when(donationRepository).save(any(DonationEntity.class));

        PendingDonation pendingDonation = new PendingDonation(234, "4242424242424242", "08", "2012", 45, 87);

        doReturn(charge).when(stripeService).createCharge(pendingDonation);

        DonationResponse response = donationService.create(pendingDonation);

        assertEquals(123, response.getAmount());

        ArgumentCaptor<DonationEntity> donationEntityCaptor = ArgumentCaptor.forClass(DonationEntity.class);
        verify(donationRepository).save(donationEntityCaptor.capture());

        DonationEntity donationEntity = donationEntityCaptor.getValue();

        assertEquals(123, donationEntity.getAmount());
        assertEquals("REMOTE-ID-123", donationEntity.getRemoteId());
        assertEquals(45, donationEntity.getCampaignId());
        assertEquals(87, donationEntity.getUserId());
    }

    @Test
    public void testCreateFailure() throws Exception {
        PendingDonation pendingDonation = new PendingDonation(234, "4242424242424242", "08", "2012", 45, 87);

        doReturn(null).when(stripeService).createCharge(pendingDonation);

        DonationResponse response = donationService.create(pendingDonation);

        assertNull(response);

        verifyZeroInteractions(donationRepository);
    }

    @Test
    public void testGetDonationTotal() throws Exception {
        doReturn(100L).when(donationRepository).findTotalForCampaign(10L);

        long result = donationService.getDonationTotal(10L);

        assertEquals(100L, result);
    }

    @Test
    public void testGetDonationsForUser() throws Exception {

        List<DonationEntity> donations = asList(mock(DonationEntity.class));

        doReturn(donations).when(donationRepository).findForUser(10L);

        Iterable<DonationEntity> result = donationService.getDonationsForUser(10L);

        assertEquals(donations, result);
    }
}