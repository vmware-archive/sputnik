package com.sputnik.donation;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "donations")
public class DonationEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private long amount;

    @Column(name="userid")
    private long userId;

    @Column(name="donationeventid")
    private long donationEventId;

    @Column(name="remoteid")
    private String remoteId;

    @Column(name="createdat", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date createdAt;

    @ManyToOne(optional=false)
    @JoinColumn(name="donationeventid",referencedColumnName="id", insertable = false, updatable = false)
    private DonationEvent donationEvent;

    public DonationEntity(long amount, long userId, long donationEventId, String remoteId) {
        this.amount = amount;
        this.userId = userId;
        this.donationEventId = donationEventId;
        this.remoteId = remoteId;
    }

    public DonationEntity() {
    }

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    public long getId() {
        return id;
    }

    public long getAmount() {
        return amount;
    }

    public long getUserId() {
        return userId;
    }

    public long getDonationEventId() { return donationEventId; }

    public String getRemoteId() {
        return remoteId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public DonationEvent getDonationEvent() {
        return donationEvent;
    }
}
