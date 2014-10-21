package com.sputnik.donation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class DonationsController {

    @Autowired
    private DonationService donationService;


    @RequestMapping(method = RequestMethod.GET, value = "/donations")
    public
    @ResponseBody
    List<DonationResponse> getDonationsForPrincipal(Principal principal) {
        Iterable<DonationEntity> donations = donationService.getDonationsForUser(new Long(principal.getName()));

        return StreamSupport.stream(donations.spliterator(), false).map(d -> {
            return new DonationResponse(d.getAmount(), d.getCampaign(), d.getCreatedAt());
        }).collect(Collectors.toList());
    }

}
