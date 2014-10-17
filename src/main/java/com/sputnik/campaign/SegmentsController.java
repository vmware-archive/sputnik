package com.sputnik.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SegmentsController {
    @Autowired
    private SegmentRepository segmentRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/segments")
    public
    @ResponseBody
    Iterable<SegmentEntity> getAllSegments() {
        return segmentRepository.findWithCampaign();
    }
}
