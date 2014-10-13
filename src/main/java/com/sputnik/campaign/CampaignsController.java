package com.sputnik.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CampaignsController {

    @Autowired
    private CampaignService campaignService;

    @RequestMapping(method = RequestMethod.GET, value = "/campaigns")
    public
    @ResponseBody
    Iterable<Campaign> getAllSegmentEfforts(@RequestParam(required = false) String activityId) {
        if(activityId == null) {
            return campaignService.findAll();
        } else {
            return campaignService.findForActivityId(activityId);
        }
    }
}
