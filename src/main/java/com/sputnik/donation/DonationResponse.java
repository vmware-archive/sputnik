package com.sputnik.donation;

import java.util.Date;

public class DonationResponse {
    private long amount;
    private DonationEvent donationEvent;
    private Date createdAt;

    public DonationResponse(long amount, DonationEvent donationEvent, Date createdAt) {
        this.amount = amount;
        this.donationEvent = donationEvent;
        this.createdAt = createdAt;
    }

    public long getAmount() {
        return amount;
    }

    public DonationEvent getDonationEvent() {
        return donationEvent;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
