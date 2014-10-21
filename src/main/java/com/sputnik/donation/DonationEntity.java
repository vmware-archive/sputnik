package com.sputnik.donation;

import com.sputnik.campaign.Campaign;

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

    @Column(name="campaignid")
    private long campaignId;

    @Column(name="remoteid")
    private String remoteId;

    @Column(name="createdat", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date createdAt;

    @ManyToOne(optional=false)
    @JoinColumn(name="campaignid",referencedColumnName="id", insertable = false, updatable = false)
    private Campaign campaign;

    public DonationEntity(long amount, long userId, long campaignId, String remoteId) {
        this.amount = amount;
        this.userId = userId;
        this.campaignId = campaignId;
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

    public long getCampaignId() {
        return campaignId;
    }

    public String getRemoteId() {
        return remoteId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Campaign getCampaign() {
        return campaign;
    }
}
