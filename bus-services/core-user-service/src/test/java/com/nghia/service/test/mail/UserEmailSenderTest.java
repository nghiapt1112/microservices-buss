package com.nghia.service.test.mail;

import com.nghia.base.config.BaseServiceTest;
import com.nghia.libraries.email.infrustructure.mail.EmailException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserEmailSenderTest extends BaseServiceTest {

    @Autowired
    private EmailServiceWrongTemplate emailSenderTest;

    @Autowired
    private EmailServiceNullInfo emailServiceNullInfo;

    @Autowired
    private EmailServiceSuccess emailServiceSuccess;


    @Test(expected = EmailException.class)
    public void wrongTemplate() {
        emailSenderTest.send();
    }

    @Test(expected = EmailException.class)
    public void sendWithNullInfo() {
        emailServiceNullInfo.send();
    }

    @Test
    public void send() {
        emailServiceSuccess.send();
    }

}
