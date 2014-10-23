package com.sputnik.donation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationEventRepository extends CrudRepository<DonationEvent, Long> {
    public DonationEvent findByCampaignIdAndActivityId(long campaignId, long activityId);
}
