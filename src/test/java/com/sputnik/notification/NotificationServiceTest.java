package com.sputnik.notification;

import com.sendgrid.SendGrid.Email;
import com.sputnik.strava.segmenteffort.SegmentEffort;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NotificationServiceTest extends TestCase {
    @Mock
    Environment env;

    @Mock
    EmailBuilder emailBuilder;

    @Mock
    EmailService emailService;

    @InjectMocks
    NotificationService notificationService;

    @Test
    public void testNotifyUsers() throws Exception {
        doReturn("www.example.com").when(env).getProperty("baseUrl");

        Map<String, String> userInfoMap = new HashMap<>();
        userInfoMap.put("1234", "test@exmaple.com");
        userInfoMap.put("7890", "sample@exmaple.com");

        SegmentEffort testEffort = new SegmentEffort(123, "Pearl Street", "1234", 2.3F, "2006-04-21T13:20:40Z", 789, 10, 1);
        SegmentEffort sampleEffort = new SegmentEffort(124, "Pearl Street", "7890", 2.3F, "2006-04-21T13:20:40Z", 789, 10, 2);
        SegmentEffort sameActivityEffort = new SegmentEffort(125, "Pearl Street", "7890", 2.3F, "2006-04-21T13:20:40Z", 789, 10, 2);
        SegmentEffort notOurUserEffort = new SegmentEffort(126, "Pearl Street", "456", 2.3F, "2006-04-21T13:20:40Z", 789, 10, 3);

        List<SegmentEffort> allSegmentEfforts = new ArrayList<>();
        allSegmentEfforts.add(testEffort);
        allSegmentEfforts.add(sampleEffort);
        allSegmentEfforts.add(sameActivityEffort);
        allSegmentEfforts.add(notOurUserEffort);

        Email testEmail = new Email();
        Email sampleEmail = new Email();

        doReturn(testEmail).when(emailBuilder).buildFor("test@exmaple.com", testEffort, "www.example.com");
        doReturn(sampleEmail).when(emailBuilder).buildFor("sample@exmaple.com", sampleEffort, "www.example.com");

        notificationService.notifyUsers(userInfoMap, allSegmentEfforts);

        verify(emailService).send(testEmail);
        verify(emailService).send(sampleEmail);
        verifyNoMoreInteractions(emailService);
    }
}