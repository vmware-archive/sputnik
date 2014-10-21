package com.sputnik.donation;

import com.sputnik.campaign.Campaign;

import java.util.Date;

public class DonationResponse {
    private long amount;
    private Campaign campaign;
    private Date createdAt;

    public DonationResponse(long amount, Campaign campaign, Date createdAt) {
        this.amount = amount;
        this.campaign = campaign;
        this.createdAt = createdAt;
    }

    public long getAmount() {
        return amount;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
