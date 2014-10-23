package com.sputnik.donation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonationEventService {

    @Autowired
    DonationEventRepository donationEventRepository;

    public DonationEvent findOrCreate(DonationEvent donationEvent) {
        DonationEvent returnedDonationEvent = donationEventRepository
                .findByCampaignIdAndActivityId(
                        donationEvent.getCampaignId(),
                        donationEvent.getActivityId()
                );

        if (returnedDonationEvent  != null)
            return returnedDonationEvent ;

        return donationEventRepository.save(donationEvent);
    }

    public DonationEvent findById(long id) {
        return donationEventRepository.findOne(id);
    }
}
