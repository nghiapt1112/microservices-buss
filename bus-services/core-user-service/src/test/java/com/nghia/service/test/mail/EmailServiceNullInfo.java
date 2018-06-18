package com.nghia.service.test.mail;
import com.nghia.libraries.email.infrustructure.mail.Email;
import com.nghia.libraries.email.infrustructure.mail.EmailService;
import com.nghia.libraries.email.infrustructure.mail.Template;


@Template("UserEmailTemplateCreate")
public class EmailServiceNullInfo extends EmailService {
    @Override
    public Email prepareMailInfo() {return null;}


}