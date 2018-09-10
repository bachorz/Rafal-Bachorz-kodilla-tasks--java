package com.crud.tasks.domain;

import org.junit.Assert;
import org.junit.Test;

public class AttachmentAndBadgesTest {

    @Test
    public void testAttachmentAndBadges (){

        //Given & When
        TrelloDto newTrello = new TrelloDto(1, 2);
        TrelloAttachmentsDto newAttachment = new TrelloAttachmentsDto(newTrello);
        TrelloBadgesDto newBadge = new TrelloBadgesDto(5, newAttachment);

        //Then
        Assert.assertEquals(2,newTrello.getCard());
        Assert.assertEquals(1, newTrello.getBoard());
        Assert.assertEquals(5, newBadge.getVotes());
        Assert.assertEquals(1,newBadge.getAttachments().getTrello().getBoard());
        Assert.assertEquals(2,newBadge.getAttachments().getTrello().getCard());
        Assert.assertEquals(1, newAttachment.getTrello().getBoard());
        Assert.assertEquals(2, newAttachment.getTrello().getCard());
    }
}
