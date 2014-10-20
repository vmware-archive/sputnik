package com.sputnik.donation;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRepository extends CrudRepository<DonationEntity, Long> {

    @Query("select coalesce(sum(d.amount), 0) as amount from DonationEntity d where d.campaignId = :campaignId")
    public long findTotalForCampaign(@Param("campaignId") long campaignId);

}
