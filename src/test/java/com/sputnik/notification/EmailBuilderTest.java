package com.sputnik.notification;

import com.sendgrid.SendGrid.Email;
import com.sputnik.strava.segmenteffort.SegmentEffort;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class EmailBuilderTest extends TestCase {

    @InjectMocks
    EmailBuilder emailBuilder;

    @Test
    public void testBuildFor() throws Exception {
        SegmentEffort segmentEffort = new SegmentEffort(123, "Pearl Street", "1234", 2.3F, "2006-04-21T13:20:40Z", 789, 10);
        String athleteEmail = "test@example.com";
        String baseUrl = "www.example.com";

        emailBuilder.setEmailFromAddress("sputnik@example.com");

        Email email = emailBuilder.buildFor(athleteEmail, segmentEffort, baseUrl);

        assertEquals(athleteEmail, Arrays.asList(email.getTos()).get(0));
        assertEquals("sputnik@example.com", email.getFrom());
        assertEquals("Your ride on Pearl Street", email.getSubject());
        assertEquals("Visit sputnik to <a href=\"www.example.com/#/segmentEfforts/123\">donate</a>.", email.getHtml());
    }
}