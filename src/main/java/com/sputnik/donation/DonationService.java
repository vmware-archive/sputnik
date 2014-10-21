package com.sputnik.donation;

import com.sputnik.campaign.Campaign;
import com.sputnik.campaign.CampaignService;
import com.sputnik.persistence.User;
import com.sputnik.persistence.UserRepository;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonationService {
    @Autowired
    StripeService stripeService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CampaignService campaignService;

    @Autowired
    DonationRepository donationRepository;

    public DonationResponse create(PendingDonation pendingDonation) {
        User user = userRepository.findOne(pendingDonation.getUserId());
        Campaign campaign = campaignService.findById(pendingDonation.getCampaignId());

        Charge charge = stripeService.createCharge(pendingDonation, user, campaign);

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
