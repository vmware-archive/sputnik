package com.sputnik.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SegmentEntityService {

    @Autowired
    SegmentRepository segmentRepository;

    @Autowired
    CampaignRepository campaignRepository;

    public Iterable<SegmentEntity> findAll() {
        return segmentRepository.findAll();
    }

    public SegmentEntity create(SegmentEntity segment) {
        return segmentRepository.save(segment);
    }

    public void delete(long id) {
        segmentRepository.delete(id);
    }

    public void addCampaign(long segmentEntityId, long campaignId) {
        SegmentEntity segmentEntity = segmentRepository.findOne(segmentEntityId);
        Campaign campaign = campaignRepository.findOne(campaignId);

        segmentEntity.addCampaign(campaign);
        segmentRepository.save(segmentEntity);
    }

    public void removeCampaign(long segmentEntityId, long campaignId) {
        SegmentEntity segmentEntity = segmentRepository.findOne(segmentEntityId);

        segmentEntity.removeCampaign(campaignId);
        segmentRepository.save(segmentEntity);
    }
}
