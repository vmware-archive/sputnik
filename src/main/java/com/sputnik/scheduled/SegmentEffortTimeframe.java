package com.sputnik.scheduled;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

@Component
public class SegmentEffortTimeframe {

    @Value("${timezone}")
    String timezone;

    private Instant now;

    public SegmentEffortTimeframe() {
        this.now = Instant.now();
    }

    public String getStartTime() {
        return now.minus(Duration.ofDays(1)).atZone(ZoneId.of(timezone)).toString();
    }

    public String getEndTime() {
        return now.atZone(ZoneId.of(timezone)).toString();
    }
}
