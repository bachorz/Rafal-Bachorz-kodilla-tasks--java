package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.scheduler.EmailScheduler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmailSchedulerTest {

    @InjectMocks
    private EmailScheduler emailScheduler;

    @Mock
    private SimpleEmailService simpleEmailService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private AdminConfig adminConfig;

//    @Mock
//    private Mail mail;

    @Test
    public void sendInformationEmailTest () {

        //Given
//        Mail mail = new Mail("test@test.com", "Test", "Test Massage","test2@test2.com") ;
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(mail.getMailTo());
//        mailMessage.setSubject(mail.getSubject());
//        mailMessage.setText(mail.getMassage());
//        mailMessage.setCc(mail.getToCC());
        when(adminConfig.getAdminMail()).thenReturn("jakisuzytkownik666@gmail.com");

        //When
        emailScheduler.sendInformationEmail();

        //Then
 //       verify(simpleEmailService, times(1).send(mailMessage))
        verify(simpleEmailService, times(1)).send(any(Mail.class));

    }


}
