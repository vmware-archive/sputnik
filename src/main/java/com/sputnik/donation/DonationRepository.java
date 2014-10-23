package com.sputnik.donation;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRepository extends CrudRepository<DonationEntity, Long> {

    @Query("select coalesce(sum(d.amount), 0) as amount from DonationEntity d INNER JOIN d.donationEvent de where de.campaignId = :campaignId")
    public long findTotalForCampaign(@Param("campaignId") long campaignId);

    @Query("select d from DonationEntity d where d.userId = :userId order by d.createdAt desc")
    public Iterable<DonationEntity> findForUser(@Param("userId") long userId);
}
