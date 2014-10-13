package com.sputnik.notification;

import com.sendgrid.SendGrid.Email;
import com.sputnik.strava.segmenteffort.SegmentEffort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailBuilder {

    private String emailFromAddress;

    @Value("${emailFromAddress}")
    public void setEmailFromAddress(String emailFromAddress) {
        this.emailFromAddress = emailFromAddress;
    }

    public Email buildFor(String athleteEmail, SegmentEffort effort, String baseUrl) {
        Email email = new Email();
        email.addTo(athleteEmail);
        email.setFrom(emailFromAddress);
        email.setSubject("Your ride on " + effort.getName());
        email.setHtml(
                "Visit sputnik to <a href=\"" + baseUrl + "/#/activities/" + String.valueOf(effort.getActivityId()) + "\">donate</a>."
        );

        return email;
    }
}
