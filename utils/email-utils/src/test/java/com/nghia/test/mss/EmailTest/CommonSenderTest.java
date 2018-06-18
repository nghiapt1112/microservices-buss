package com.nghia.test.mss.EmailTest;

import com.nghia.libraries.email.infrustructure.mail.Email;
import com.nghia.libraries.email.infrustructure.mail.EmailService;
import com.nghia.libraries.email.infrustructure.mail.Template;

@Template("commonTestTemplateFile")
public class CommonSenderTest extends EmailService {

    @Override
    public Email prepareMailInfo() {
        Email emailInfo = new Email("tuannghiatoregister@gmail.com", "tuannghia.sun@gmail.com");
        emailInfo.setSubject("Subject of Email2" + System.currentTimeMillis());

        return emailInfo;
    }

}
