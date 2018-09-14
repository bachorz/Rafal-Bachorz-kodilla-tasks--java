package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TrelloValidatorTest {

    private TrelloValidator trelloValidator = new TrelloValidator();

    @Test
    public void validateTrelloBoardsTest () {

        //Given
        List<TrelloList> list = Arrays.asList(new TrelloList("1", "List1", true),
                new TrelloList("2", "List2", false));
        List<TrelloList> list2 = Arrays.asList(new TrelloList("3", "List3", true),
                new TrelloList("1", "List1", true));
        List<TrelloBoard> boardList = Arrays.asList(new TrelloBoard("A 01", "test", list),
                new TrelloBoard("B 01", "Board B", list2));

        //When
        List<TrelloBoard> listResult = trelloValidator.validateTrelloBoards(boardList);

        //Then
        Assert.assertEquals(1, listResult.size());
        Assert.assertEquals("Board B", listResult.get(0).getName());
        Assert.assertEquals(list2, listResult.get(0).getLists());
    }

    @Test
    public void validateCardTest () {

        //Given
        TrelloCard trelloCard = new TrelloCard("Task","Task on today", "top","A-113");

        //When & Then
        trelloValidator.validateCard(trelloCard);
    }
}
