package com.crud.tasks.domain;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TrelloDtoTest {

    @Test
    public void testTrelloDto (){

        //Give
        TrelloDto trelloDto = new TrelloDto(1,3);

        //When & Then
        Assert.assertEquals(1,trelloDto.getBoard());
        Assert.assertEquals(3,trelloDto.getCard());
    }
}
