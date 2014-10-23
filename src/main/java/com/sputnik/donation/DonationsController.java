package com.sputnik.donation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private DonationEventService donationEventService;


    @RequestMapping(method = RequestMethod.GET, value = "/donations")
    public
    @ResponseBody
    List<DonationResponse> getDonationsForPrincipal(Principal principal) {
        Iterable<DonationEntity> donations = donationService.getDonationsForUser(new Long(principal.getName()));

        return StreamSupport.stream(donations.spliterator(), false).map(d -> {
            return new DonationResponse(d.getAmount(), d.getDonationEvent(), d.getCreatedAt());
        }).collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/donationEvents/{donationEventId}/donation")
    public
    @ResponseBody
    ResponseEntity<DonationResponse> donateToCampaign(
            @PathVariable long donationEventId,
            @RequestBody(required = true) PendingDonation donation,
            Principal principal
    ) {
        donation.setDonationEventId(donationEventId);
        donation.setUserId(new Long(principal.getName()));

        DonationResponse donationResponse = donationService.create(donation);

        if (donationResponse == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(donationResponse, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/donationEvents/{donationEventId}")
    public
    @ResponseBody
    ResponseEntity<DonationEvent> donateToCampaign(
            @PathVariable long donationEventId
    ) {

        DonationEvent donationEvent = donationEventService.findById(donationEventId);

        if (donationEvent == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(donationEvent, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/donationEvents")
    public
    @ResponseBody
    ResponseEntity<DonationEvent> findOrCreateDonationEvent(
            @RequestBody(required = true) DonationEvent donationEvent,
            Principal principal
    ) {
        donationEvent.setAthleteId(new Long(principal.getName()));

        DonationEvent returnedDonationEvent = donationEventService.findOrCreate(donationEvent);

        return new ResponseEntity<>(returnedDonationEvent, HttpStatus.OK);
    }
}
