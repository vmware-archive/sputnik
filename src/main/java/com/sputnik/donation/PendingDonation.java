package com.sputnik.donation;

public class PendingDonation {
    private long amount;
    private String cardNumber;
    private String cardExpirationMonth;
    private String cardExpirationYear;
    private long campaignId;
    private long userId;

    public PendingDonation(long amount, String cardNumber, String cardExpirationMonth, String cardExpirationYear, long campaignId, long userId) {
        this.amount = amount;
        this.cardNumber = cardNumber;
        this.cardExpirationMonth = cardExpirationMonth;
        this.cardExpirationYear = cardExpirationYear;
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

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardExpirationMonth() {
        return cardExpirationMonth;
    }

    public String getCardExpirationYear() {
        return cardExpirationYear;
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
