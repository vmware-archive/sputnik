package com.sputnik.admin;

import com.sputnik.campaign.Campaign;
import com.sputnik.campaign.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
