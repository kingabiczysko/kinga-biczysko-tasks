package com.crud.tasks.trello.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {

    @Value("${admin.name}")
    private String adminName;

    @Value("${admin.mail}")
    private String adminMail;

    @Value("${info.company.name}")
    private String companyName;

    @Value("${info.app.administrator.address.street}")
    private String companyStreet;

    @Value("${info.app.administrator.address.number}")
    private String companyStreetNumber;

    @Value("${info.app.administrator.address.town}")
    private String companyTown;

    @Value("${info.company.phone}")
    private String companyPhone;
}
