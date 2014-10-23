package com.sputnik.campaign;

import com.sputnik.donation.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.GET, value = "/campaigns/{id}/donate")
    public
    @ResponseBody
    long getDonationTotal(@PathVariable long id) {
        return donationService.getDonationTotal(id);
    }

}
