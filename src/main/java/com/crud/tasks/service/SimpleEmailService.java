package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.MailType;
import lombok.EqualsAndHashCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import static com.crud.tasks.domain.MailType.TASK_INFO;
import static com.crud.tasks.domain.MailType.TRELLO_CARD;


@EqualsAndHashCode
@Service
public class SimpleEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);


    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailCreatorService mailCreatorService;

    public void send(final Mail mail, MailType mailType) {
        LOGGER.info("Starting email preparation...");
        try {
            javaMailSender.send(createMimeMessage(mail, mailType));
            LOGGER.info("Email has been sent.");
        }catch (MailException e) {
            LOGGER.error("Failed to process email sending: ", e.getMessage(), e);
        }
    }

    private MimeMessagePreparator createMimeMessage(final Mail mail, MailType mailType) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            String message = getMessage(mail, mailType);
            if (message != null) {
                messageHelper.setText(message, true);
            }
        };
    }

    private String getMessage(final Mail mail, MailType mailType) {
        if (mailType == TASK_INFO) {
            return mailCreatorService.buildTaskInfoEmail(mail.getMessage());
        }
        if (mailType == TRELLO_CARD) {
            return mailCreatorService.buildTaskInfoEmail(mail.getMessage());
        }
        return null;
    }
}


