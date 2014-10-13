package com.sputnik.campaign;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "segments")
public class Segment implements Serializable {

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

    public Segment(long remoteid) {
        this.remoteid = remoteid;
    }

    public Segment() {
    }

    public long getId() {
        return id;
    }

    public long getRemoteid() {
        return remoteid;
    }
}
