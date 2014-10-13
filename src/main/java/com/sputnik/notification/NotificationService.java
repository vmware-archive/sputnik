package com.sputnik.notification;

import com.sendgrid.SendGrid.Email;
import com.sputnik.strava.segmenteffort.SegmentEffort;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class NotificationService {
    @Inject
    Environment env;

    @Inject
    EmailBuilder emailBuilder;

    @Inject
    EmailService emailService;

    public void notifyUsers(Map<String, String> userInfoMap, List<SegmentEffort> allSegmentEfforts) {
        String baseUrl = env.getProperty("baseUrl");
        Set<Long> sentActivityIds = new HashSet<>();

        allSegmentEfforts.forEach(effort -> {
            String athleteEmail = userInfoMap.get(effort.getAthleteId());

            if (athleteEmail != null) {
                Long activityId = effort.getActivityId();

                if(!sentActivityIds.contains(activityId)) {
                    sentActivityIds.add(activityId);

                    Email email = emailBuilder.buildFor(athleteEmail, effort, baseUrl);
                    emailService.send(email);
                }
            }
        });
    }
}