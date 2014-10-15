package com.sputnik.donation;

public class DonationResponse {
    private long amount;
    private long campaignId;

    public DonationResponse(long amount, long campaignId) {
        this.amount = amount;
        this.campaignId = campaignId;
    }

    public long getAmount() {
        return amount;
    }

    public long getCampaignId() {
        return campaignId;
    }
}
