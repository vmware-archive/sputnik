package com.sputnik.admin;

import com.sputnik.campaign.Campaign;
import com.sputnik.campaign.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminCampaignsController {
    @Autowired
    private CampaignService campaignService;

    @RequestMapping(method = RequestMethod.GET, value = "/admin/campaigns")
    public
    @ResponseBody
    Iterable<Campaign> getAllCampaigns() {
        return campaignService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/admin/campaigns")
    public
    @ResponseBody
    ResponseEntity<Campaign> donateToCampaign(
            @RequestBody(required=true) Campaign campaign
    ) {
        Campaign createdCampaign = campaignService.create(campaign);

        if(createdCampaign == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createdCampaign, HttpStatus.CREATED);
    }
}
