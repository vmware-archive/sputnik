package com.sputnik.admin;

import com.sputnik.campaign.SegmentEntity;
import com.sputnik.campaign.SegmentEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminSegmentsController {
    @Autowired
    private SegmentEntityService segmentService;

    @RequestMapping(method = RequestMethod.GET, value = "/admin/segments")
    public
    @ResponseBody
    Iterable<SegmentEntity> getAllSegments() {
        return segmentService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/admin/segments")
    public
    @ResponseBody
    ResponseEntity<SegmentEntity> createSegment(
            @RequestBody(required = true) SegmentEntity segment
    ) {
        SegmentEntity createdSegment = segmentService.create(segment);

        if (createdSegment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createdSegment, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/admin/segments/{id}")
    public
    @ResponseBody
    void deleteSegment(
            @PathVariable long id
    ) {
        segmentService.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/admin/segments/{id}/campaigns/{campaignId}")
    public
    @ResponseBody
    ResponseEntity<String> addCampaign(
            @PathVariable long id,
            @PathVariable long campaignId
    ) {
        segmentService.addCampaign(id, campaignId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/admin/segments/{id}/campaigns/{campaignId}")
    public
    @ResponseBody
    void removeCampaign(
            @PathVariable long id,
            @PathVariable long campaignId
    ) {
        segmentService.removeCampaign(id, campaignId);
    }

}
