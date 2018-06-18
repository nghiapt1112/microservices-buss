package com.nghia.libraries.email.infrustructure.mail;

import com.nghia.libraries.email.infrustructure.service.GenericAbstractService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;

import static javax.mail.Message.RecipientType.TO;


public abstract class EmailService extends GenericAbstractService {
    protected static final Logger MAIL_LOGGER = LoggerFactory.getLogger(EmailService.class);
    @Autowired
    private JavaMailSender sender;

    @Value("${mail.content}")
    private String CONTENT_TYPE;

    @Autowired
    private TaskExecutor executor;

    private String BASE_DIR = "mail/template/";
    private String mailContent;
    private String templatePath;
    private Email mail;

    final public void send() {
        this.parseTemplatePath();
        this.parseMessage();

        this.parseMailInfo();
        this.validateMailInfo();

//        executor.execute(() -> {
            try {
                sender.send(this.getMime());
            } catch (Exception e) {
                throw new EmailException(toInt("mail.send.code"), toStr("mail.send.msg"), e);
            }
//        });

    }

    private void parseMailInfo() {
        this.mail = this.prepareMailInfo();
    }

    private void parseTemplatePath() {
        // TODO: check null
        Template emailTemplate = this.getClass().getAnnotation(Template.class);
        if (Objects.isNull(emailTemplate)) {
            throw new EmailException(toInt("mail.template_NOT_DEFINED.code"), toStr("mail.template_NOT_DEFINED.msg"));
        }
        templatePath = emailTemplate.value();
    }


    private MimeMessagePreparator getMime() {
        return (mimeMsg) -> {
            mimeMsg.setFrom(mail.getFrom());
            mimeMsg.setRecipient(TO, mail.getTo());
            mimeMsg.setSubject(mail.getSubject());

            if (mail.hasAttachment()) {
                MimeBodyPart messageContent = new MimeBodyPart();
                messageContent.setContent(this.mailContent, CONTENT_TYPE);

                Multipart multiPart = mail.multipart();
                multiPart.addBodyPart(messageContent, 0);

                mimeMsg.setContent(multiPart);
            } else {
                mimeMsg.setContent(this.mailContent, CONTENT_TYPE);
            }
        };
    }

    protected void parseMessage() {
        // TODO: check valid input
//        if (StringUtils.isEmpty(templatePath)) {
//            throw new EmailException(toInt("mail.template.code"), toStr("mail.template.msg"));
//        }

        try (InputStream is = new ClassPathResource(BASE_DIR.concat(templatePath)).getInputStream()) {
            String templateString = IOUtils.toString(is, Charset.defaultCharset());
            this.mailContent = templateString;
        } catch (IOException e) {
            throw new EmailException(toInt("mail.template.code"), toStr("mail.template.msg"), e);
        }
    }

    private void validateMailInfo() {
        if (Objects.isNull(mail)) {
            throw new EmailException(toInt("mail.info.code"), toStr("mail.info.msg"));

        }
    }

    abstract protected Email prepareMailInfo();
}
