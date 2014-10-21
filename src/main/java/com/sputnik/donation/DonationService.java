package com.sputnik.donation;

import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonationService {
    @Autowired
    StripeService stripeService;

    @Autowired
    DonationRepository donationRepository;

    public DonationResponse create(PendingDonation pendingDonation) {
        Charge charge = stripeService.createCharge(pendingDonation);

        if(charge == null) {
            return null;
        }

        DonationEntity donationEntity = new DonationEntity(charge.getAmount(), pendingDonation.getUserId(), pendingDonation.getCampaignId(), charge.getId());
        donationEntity = donationRepository.save(donationEntity);

        return new DonationResponse(charge.getAmount(), donationEntity.getCampaign(), donationEntity.getCreatedAt());
    }

    public long getDonationTotal(long campaignId) {
        return donationRepository.findTotalForCampaign(campaignId);
    }

    public Iterable<DonationEntity> getDonationsForUser(long userId) { return donationRepository.findForUser(userId); }
}
