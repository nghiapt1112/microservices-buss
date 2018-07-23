package com.nghia.service.test.mail;
import com.nghia.libraries.email.infrustructure.mail.Email;
import com.nghia.libraries.email.infrustructure.mail.EmailService;
import com.nghia.libraries.email.infrustructure.mail.Template;

@Template("UserEmailTemplateCreate1")
public class EmailServiceWrongTemplate extends EmailService {
    @Override
    public Email prepareMailInfo() {
        Email emailInfo = new Email("tuannghiatoregister@gmail.com", "tuannghia.sun@gmail.com");
        emailInfo.setSubject("Subject of Emailxxxxxxxxxxxxxxxx");

        return emailInfo;
    }
}