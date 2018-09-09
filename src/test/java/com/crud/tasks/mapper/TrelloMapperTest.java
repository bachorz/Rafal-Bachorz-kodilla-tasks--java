package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TrelloMapperTest {

    TrelloMaper trelloMaper = new TrelloMaper();

    @Test
    public void mapToBardsTest (){

        //Given
        List<TrelloListDto> list = new ArrayList<>();
        list.add(new TrelloListDto("List1", "1", true));
        list.add(new TrelloListDto("List2", "2", false));

        List<TrelloBoardDto> boardDtoList = new ArrayList<>();
        boardDtoList.add(new TrelloBoardDto("Board A", "A 01", list));

        //When
        List<TrelloBoard> listResult  = trelloMaper.mapToBards(boardDtoList);

        //Then
        Assert.assertEquals(1, listResult.size());
        Assert.assertEquals(2,listResult.get(0).getLists().size());
        Assert.assertEquals("Board A", listResult.get(0).getName());
    }

    @Test
    public void mapToBoardsDtoTest () {

        //Give
        List<TrelloList> list = new ArrayList<>();
        List<TrelloList> list2 = new ArrayList<>();
        list.add(new TrelloList("1", "List1", true));
        list.add(new TrelloList("2", "List2", false));
        list2.add(new TrelloList("3", "List3", true));
        list2.add(new TrelloList("1", "List1", true));

        List<TrelloBoard> boardList = new ArrayList<>();
        boardList.add(new TrelloBoard("Board A", "A 01", list));
        boardList.add(new TrelloBoard("Board B", "B 01", list2));

        //When
        List<TrelloBoardDto> listResult = trelloMaper.mapToBoardsDto(boardList);

        //Then
        Assert.assertEquals(2,listResult.size());
        Assert.assertEquals("List3", listResult.get(1).getLists().get(0).getName());
    }

    @Test
    public void mapToListTest (){

        //Given
        List<TrelloListDto> list = new ArrayList<>();
        list.add(new TrelloListDto("1", "List1", true));
        list.add(new TrelloListDto("2", "List2", false));
        list.add(new TrelloListDto("3", "List3", true));

        //When
        List<TrelloList> listResult = trelloMaper.mapToList(list);

        //Then
        Assert.assertEquals(3, listResult.size());
        Assert.assertEquals(false, listResult.get(1).isClosed());
    }

    @Test
    public void mapToListDtoTest (){

        //Given
        List<TrelloList> list = new ArrayList<>();
        list.add(new TrelloList("1", "List1", true));
        list.add(new TrelloList("2", "List2", false));
        list.add(new TrelloList("3", "List3", true));

        //When
        List<TrelloListDto> listResult = trelloMaper.mapToListDto(list);

        //Then
        Assert.assertEquals(3,listResult.size());
        Assert.assertEquals("2", listResult.get(1).getId());
    }

    @Test
    public void mapToCardDtoTest (){

        //Given
        TrelloCard trelloCard = new TrelloCard("Card A", "Example description", "last", "3");

        //When
        TrelloCardDto resultTrelloCard = trelloMaper.mapToCardDto(trelloCard);

        //Then
        Assert.assertEquals("Example description", resultTrelloCard.getDescription());
    }

    @Test
    public void mapToCardTest (){

        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card A", "Example description", "top", "3");

        //When
        TrelloCard resultTrelloCard = trelloMaper.mapToCard(trelloCardDto);

        //Then
        Assert.assertEquals("Card A", resultTrelloCard.getName());
    }
}
