package com.sputnik.notification;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGrid.Email;
import com.sendgrid.SendGrid.Response;
import com.sendgrid.SendGridException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class EmailService {
    @Inject
    SendGrid sendGrid;

    public void send(Email email) {
        try {
            Response response = sendGrid.send(email);
            System.out.println(response.getMessage());
        } catch (SendGridException e) {
            System.err.println(e);
        }
    }
}
