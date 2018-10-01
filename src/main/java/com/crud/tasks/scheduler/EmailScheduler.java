package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SchedulerEmailService;
import com.crud.tasks.service.SimpleEmailService;
import javafx.scene.input.DataFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@EqualsAndHashCode
@Component
public class EmailScheduler {

    private static final String SUBJECT = "Tasks: Once a day email";
    private static final String SUBJECT2 = "Tasks: Daily report";

    private Calendar calendar = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private SchedulerEmailService schedulerEmailService;

    @Scheduled(fixedDelay = 30000)
    public void sendInformationEmail() {
        long size = taskRepository.count();
        String word;
        if (size == 1) {
            word = " task";
        }else{
            word = " tasks";
        }
        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                "Currently in database you got: " + size + word,
                ""));
    }

    @Scheduled(cron = "0 0 10 * * *")
    public void sendTaskInfoEmail() {

        schedulerEmailService.sendScheduler(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT2,
                "Today is day " + dateFormat.format(calendar.getTime())+ ". Currently in database you got: ",
                ""));
    }
}
