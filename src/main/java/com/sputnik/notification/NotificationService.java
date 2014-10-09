package com.sputnik.notification;

import com.sendgrid.SendGrid.Email;
import com.sputnik.strava.segmenteffort.SegmentEffort;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

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

        allSegmentEfforts.forEach(effort -> {
            String athleteEmail = userInfoMap.get(effort.getAthleteId());

            if (athleteEmail != null) {
                Email email = emailBuilder.buildFor(athleteEmail, effort, baseUrl);
                emailService.send(email);
            }
        });
    }
}