package com.sputnik.notification;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGrid.Email;
import com.sendgrid.SendGrid.Response;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest extends TestCase {
    @Mock
    SendGrid sendGrid;

    @InjectMocks
    EmailService emailService;

    @Test
    public void testSend() throws Exception {
        Email email = mock(Email.class);

        doReturn(mock(Response.class)).when(sendGrid).send(email);
        emailService.send(email);

        verify(sendGrid).send(email);
    }
}