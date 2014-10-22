package com.sputnik.donation;

public class PendingDonation {
    private long amount;
    private String token;
    private long campaignId;
    private long userId;

    public PendingDonation(long amount, String token, long campaignId, long userId) {
        this.amount = amount;
        this.token = token;
        this.campaignId = campaignId;
        this.userId = userId;
    }

    public PendingDonation() {
    }

    public long getAmount() {
        return amount;
    }
    public long getDollarAmount() {
        return amount * 100;
    }

    public String getToken() {
        return token;
    }

    public long getCampaignId() {
        return campaignId;
    }

    public long getUserId() {
        return userId;
    }

    public void setCampaignId(long campaignId) {
        this.campaignId = campaignId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
