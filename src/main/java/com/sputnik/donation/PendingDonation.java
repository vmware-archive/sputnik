package com.sputnik.donation;

public class PendingDonation {
    private long amount;
    private String token;
    private long donationEventId;
    private long userId;

    public PendingDonation(long amount, String token, long donationEventId, long userId) {
        this.amount = amount;
        this.token = token;
        this.donationEventId = donationEventId;
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

    public long getDonationEventId() {
        return donationEventId;
    }

    public long getUserId() {
        return userId;
    }

    public void setDonationEventId(long donationEventId) {
        this.donationEventId = donationEventId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
