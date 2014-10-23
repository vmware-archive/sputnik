package com.sputnik.donation;

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
    DonationEventService donationEventService;

    @Autowired
    DonationRepository donationRepository;

    public DonationResponse create(PendingDonation pendingDonation) {
        User user = userRepository.findOne(pendingDonation.getUserId());
        DonationEvent donationEvent = donationEventService.findById(pendingDonation.getDonationEventId());

        Charge charge = stripeService.createCharge(pendingDonation, user, donationEvent.getCampaign());

        if(charge == null) {
            return null;
        }

        DonationEntity donationEntity = new DonationEntity(charge.getAmount(), pendingDonation.getUserId(), pendingDonation.getDonationEventId(), charge.getId());
        donationEntity = donationRepository.save(donationEntity);

        return new DonationResponse(charge.getAmount(), donationEntity.getDonationEvent(), donationEntity.getCreatedAt());
    }

    public long getDonationTotal(long campaignId) {
        return donationRepository.findTotalForCampaign(campaignId);
    }

    public Iterable<DonationEntity> getDonationsForUser(long userId) { return donationRepository.findForUser(userId); }
}
