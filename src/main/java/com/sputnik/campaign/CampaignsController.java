package com.sputnik.campaign;

import com.sputnik.donation.DonationResponse;
import com.sputnik.donation.DonationService;
import com.sputnik.donation.PendingDonation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class CampaignsController {

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private DonationService donationService;

    @RequestMapping(method = RequestMethod.GET, value = "/campaigns")
    public
    @ResponseBody
    Iterable<Campaign> getAllCampaigns(@RequestParam(required = false) String activityId) {
        if (activityId == null) {
            return campaignService.findAll();
        } else {
            return campaignService.findForActivityId(activityId);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/campaigns/{id}")
    public
    @ResponseBody
    Campaign getCampaign(@PathVariable long id) {
        return campaignService.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/campaigns/{id}/donate")
    public
    @ResponseBody
    ResponseEntity<DonationResponse> donateToCampaign(
            @PathVariable long id,
            @RequestBody(required=true) PendingDonation donation,
            Principal principal
    ) {

        donation.setCampaignId(id);
        donation.setUserId(new Long(principal.getName()));

        DonationResponse donationResponse = donationService.create(donation);

        if(donationResponse == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(donationResponse, HttpStatus.CREATED);
    }
}
