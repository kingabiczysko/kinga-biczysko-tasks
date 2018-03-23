package com.crud.tasks.service;

import com.crud.tasks.trello.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;


    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("task_url", "http://localhost:8888/tasks_frontend");
        context.setVariable("button", "Visit webside");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbyeMessage", "Bye, see you at the next message :)");
        context.setVariable("company_details", adminConfig.getCompanyName() + ", "
                + adminConfig.getCompanyStreet() + " " + adminConfig.getCompanyStreetNumber() +
                ", " + adminConfig.getCompanyTown() + ", " + adminConfig.getCompanyPhone());
        return templateEngine.process("mail/created-trello-card-mail2", context);
    }
}
