package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.Mock;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import static com.crud.tasks.domain.MailType.TASK_INFO;
import static com.crud.tasks.domain.MailType.TRELLO_CARD;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {
    @InjectMocks
    private SimpleEmailService simpleEmailService;
    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void shouldSendEmail() {
        // Given
        Mail mail = new Mail("test@test.com", "Test", "Test Massage","test2@test2.com");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        mailMessage.setCc(mail.getToCC());
        // When
        simpleEmailService.send(mail, TASK_INFO);
        // Then
        verify(javaMailSender, times(1)).send(any(MimeMessagePreparator.class));
    }

    @Test
    public void emptyFiledSendEmail() {
        // Given
        Mail mail = new Mail("test@test.com", "Test - empty field", "Test message at an empty field",null);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
       // mailMessage.setCc(mail.getToCC());
        // When
        simpleEmailService.send(mail, TRELLO_CARD);
        // Then
        verify(javaMailSender, times(1)).send(any(MimeMessagePreparator.class));
}
}