package com.crud.tasks.config;

import org.junit.Assert;
import org.junit.Test;

public class AdminConfigTest {

    @Test
    public void testAdminConfig() {
        //Given
        AdminConfig adminConfig = new AdminConfig();
        //When
        String mail = adminConfig.getAdminMail();
        //Then
        Assert.assertEquals(null, mail);
    }
}
