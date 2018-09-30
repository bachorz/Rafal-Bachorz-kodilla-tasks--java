package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;


@Service
public class MailCreatorService {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url","http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("preview", "A new card in trello");
        context.setVariable("goodbye_message", "see you later " + adminConfig.getAdminName());
        context.setVariable("company_details", companyConfig.getCompanyName() + ", contact: " + companyConfig.getCompanyEmail() + ", phone: " + companyConfig.getCompanyPhone());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_confing", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
