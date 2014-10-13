package com.sputnik.campaign;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    @Query("select distinct c from Campaign c INNER JOIN c.segmentEntities s WHERE s.remoteid IN (:segmentIds)")
    public Iterable<Campaign> findForSegmentIds(@Param("segmentIds") List<Long> segmentIds);

}
