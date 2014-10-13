package com.sputnik.scheduled;

import com.sputnik.notification.NotificationService;
import com.sputnik.strava.segmenteffort.SegmentEffort;
import com.sputnik.strava.segmenteffort.SegmentEffortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SponsoredSegmentFinder {

    @Autowired
    SystemConnection systemConnection;

    @Autowired
    SegmentEffortTimeframe segmentEffortTimeframe;

    @Autowired
    ProviderUserEmailRepository providerUserEmailRepository;

    @Autowired
    NotificationService notificationService;

    @Scheduled(cron = "0 0 22 * * *")
//    @Scheduled(fixedRate = 120000)
    public void retrieve() {
        segmentEffortTimeframe.setNow();
        SegmentEffortService segmentEffortService = systemConnection.getSegmentEffortService();

        List<SegmentEffort> allSegmentEfforts = segmentEffortService.getAllSegmentEfforts(
                segmentEffortTimeframe.getStartTime(),
                segmentEffortTimeframe.getEndTime()
        );

        Map<String, String> userInfoMap = providerUserEmailRepository.findAll();

        notificationService.notifyUsers(userInfoMap, allSegmentEfforts);
    }
}
