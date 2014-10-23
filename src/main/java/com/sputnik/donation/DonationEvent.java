package com.sputnik.donation;

import com.sputnik.campaign.Campaign;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "donationevents")
public class DonationEvent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(name="athleteid")
    private long athleteId;

    @Column(name="campaignid")
    private long campaignId;

    @Column(name="activityid")
    private long activityId;

    @Column(name="polyline")
    private String polyline;

    @ManyToOne(optional=false)
    @JoinColumn(name="campaignid", referencedColumnName="id", insertable = false, updatable = false)
    private Campaign campaign;

    public DonationEvent(long athleteId, long campaignId, long activityId, String polyline) {
        this.athleteId = athleteId;
        this.campaignId = campaignId;
        this.activityId = activityId;
        this.polyline = polyline;
    }

    public DonationEvent() {
    }

    public long getId() {
        return id;
    }

    public long getAthleteId() {
        return athleteId;
    }

    public long getCampaignId() {
        return campaignId;
    }

    public long getActivityId() {
        return activityId;
    }

    public String getPolyline() {
        return polyline;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setAthleteId(long athleteId) {
        this.athleteId = athleteId;
    }
}
