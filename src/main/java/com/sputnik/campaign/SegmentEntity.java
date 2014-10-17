package com.sputnik.campaign;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "segments")
public class SegmentEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @ManyToMany
    @JoinTable(name="campaignsegments",
            joinColumns=@JoinColumn(name="segmentid"),
            inverseJoinColumns=@JoinColumn(name="campaignid"))
    Collection<Campaign> campaigns;

    @Column
    private long remoteid;

    public SegmentEntity(long remoteid) {
        this.remoteid = remoteid;
    }

    public SegmentEntity() {
    }

    public long getId() {
        return id;
    }

    public long getRemoteid() {
        return remoteid;
    }

    public Collection<Campaign> getCampaigns() {
        return campaigns;
    }
}
