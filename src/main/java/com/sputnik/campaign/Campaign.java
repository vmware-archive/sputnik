package com.sputnik.campaign;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "campaigns")
public class Campaign implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @ManyToMany
    @JoinTable(name="campaignsegments",
            joinColumns=@JoinColumn(name="campaignid"),
            inverseJoinColumns=@JoinColumn(name="segmentid"))
    Collection<SegmentEntity> segmentEntities;


    @Column
    private String title;

    @Column
    private String description;

    public Campaign(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Campaign() {
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
