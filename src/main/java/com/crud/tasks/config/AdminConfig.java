package com.crud.tasks.config;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@EqualsAndHashCode
public class AdminConfig {

    @Value("${admin.mail}")
    private String adminMail;
}
