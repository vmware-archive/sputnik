package com.sputnik.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SegmentEntityService {

    @Autowired
    SegmentRepository segmentRepository;

    public Iterable<SegmentEntity> findAll() {
        return segmentRepository.findAll();
    }

    public SegmentEntity create(SegmentEntity segment) {
        return segmentRepository.save(segment);
    }

    public void delete(long id) {
        segmentRepository.delete(id);
    }
}
