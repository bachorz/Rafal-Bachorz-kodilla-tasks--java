package com.crud.tasks.trello.config;

import org.junit.Assert;
import org.junit.Test;

public class TrelloConfigTest {

    TrelloConfig trelloConfig = new TrelloConfig();
    @Test
    public void getTrelloApiEndpoint() {

        //Given & When
        String apiEndpoint = trelloConfig.getTrelloApiEndpoint();
        //Then
        Assert.assertEquals(null, apiEndpoint);
    }

    @Test
    public void getTrelloAppKey() {

        //Given & When
        String appKey = trelloConfig.getTrelloAppKey();
        //Then
        Assert.assertEquals(null, appKey);
    }

    @Test
    public void getTrelloAppToken() {

        //Given & When
        String appToken = trelloConfig.getTrelloToken();
        //Then
        Assert.assertEquals(null, appToken);
    }

    @Test
    public void getTrelloUsername (){

        //Given & When
        String username = trelloConfig.getTrelloUsername();
        //Then
        Assert.assertEquals(null, username);
    }
}
