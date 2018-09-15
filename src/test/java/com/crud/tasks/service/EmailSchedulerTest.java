package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.scheduler.EmailScheduler;
import org.junit.Assert;
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

    @Test
    public void sendInformationEmailTest () {

        //Given
        when(adminConfig.getAdminMail()).thenReturn("jakisuzytkownik666@gmail.com");

        //When
        emailScheduler.sendInformationEmail();

        //Then
        verify(simpleEmailService, times(1)).send(any(Mail.class));
    }

    @Test
    public void sendTwoInformationTwoEmailTest () {

        //Given
        when(adminConfig.getAdminMail()).thenReturn("jakisuzytkownik666@gmail.com");
        when(taskRepository.count()).thenReturn(2L);

        Mail mailToCompare = new Mail("jakisuzytkownik666@gmail.com", "Tasks: Once a day email", "Currently in database you got: 2 tasks", "");

        //When
       emailScheduler.sendInformationEmail();

        //Then
        verify(simpleEmailService, times(1)).send(mailToCompare);
    }
}
