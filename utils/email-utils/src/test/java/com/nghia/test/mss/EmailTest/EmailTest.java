package com.nghia.test.mss.EmailTest;

import com.nghia.test.mss.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;


public class EmailTest extends BaseTest {
    protected static final Logger MAIL_LOGGER = LoggerFactory.getLogger("Email_Test");

    @Autowired
    private Environment env;

    @Autowired
    private CommonSenderTest testSender;

    @Before
    public void init() {
        Assert.assertNotNull(env);
        Assert.assertNotNull(testSender);
    }

    @Test
    public void sendEmail() {
        MAIL_LOGGER.info(env.getProperty("mail.smtp.host"));
        MAIL_LOGGER.info(env.getProperty("spring.data.mongodb.host"));
        testSender.send();
    }
}
