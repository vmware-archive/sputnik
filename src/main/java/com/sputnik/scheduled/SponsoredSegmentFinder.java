package com.sputnik.scheduled;

import com.sputnik.strava.StravaService;
import com.sputnik.strava.segmenteffort.SegmentEffort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SponsoredSegmentFinder {

    @Autowired
    SystemConnection systemConnection;

    @Autowired
    SegmentEffortTimeframe segmentEffortTimeframe;

    @Scheduled(cron = "0 22 * * * *")
//    @Scheduled(fixedRate = 5000)
    public void retrieve() {
        StravaService stravaService = systemConnection.getStravaService();

        List<SegmentEffort> allSegmentEfforts = stravaService.getAllSegmentEfforts(
                segmentEffortTimeframe.getStartTime(),
                segmentEffortTimeframe.getEndTime()
        );

        System.out.println("*************************************************************");
        allSegmentEfforts.forEach(effort -> System.out.println(effort.getAthleteId()));
    }
}
