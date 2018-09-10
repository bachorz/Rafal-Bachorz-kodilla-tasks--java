package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TrelloMapperTest {

    TrelloMaper trelloMaper = new TrelloMaper();

    @Test
    public void mapToBoardsTest (){

        //Given
        List<TrelloListDto> list = Arrays.asList(new TrelloListDto("1", "List1", true),
                new TrelloListDto("2", "List2", false));
        List<TrelloBoardDto> boardDtoList = Arrays.asList(new TrelloBoardDto("A 01", "Board A", list));

        List<TrelloList> listToCompare = Arrays.asList(new TrelloList("1", "List1", true),
                new TrelloList("2", "List2", false));
        List<TrelloBoard> listComparative = Arrays.asList(new TrelloBoard("A 01", "Board A", listToCompare));

        //When
        List<TrelloBoard> listResult  = trelloMaper.mapToBoards(boardDtoList);

        //Then
        Assert.assertEquals(listComparative, listResult);
    }

    @Test
    public void mapToBoardsDtoTest () {

        //Give
        List<TrelloList> list = Arrays.asList(new TrelloList("1", "List1", true),
                new TrelloList("2", "List2", false));
        List<TrelloList> list2 = Arrays.asList(new TrelloList("3", "List3", true),
                new TrelloList("1", "List1", true));
        List<TrelloBoard> boardList = Arrays.asList(new TrelloBoard("A 01", "Board A", list),
                new TrelloBoard("B 01", "Board B", list2));


        List<TrelloListDto> listToCompareA = Arrays.asList(new TrelloListDto("1", "List1", true),
                new TrelloListDto("2", "List2", false));
        List<TrelloListDto> listToCompareB = Arrays.asList(new TrelloListDto("3", "List3", true),
                new TrelloListDto("1", "List1", true));
        List<TrelloBoardDto> listComparative = Arrays.asList(new TrelloBoardDto("A 01", "Board A", listToCompareA),
                new TrelloBoardDto("B 01", "Board B", listToCompareB));

        //When
        List<TrelloBoardDto> listResult = trelloMaper.mapToBoardsDto(boardList);

        //Then
        Assert.assertEquals(listComparative,listResult);
    }
    @Test
    public void mapToListTest (){

        //Given
        List<TrelloListDto> list = Arrays.asList(new TrelloListDto("1", "List1", true),
                new TrelloListDto("2", "List2", false),new TrelloListDto("3", "List3", true));
        List<TrelloList> listComparative = Arrays.asList(new TrelloList("1", "List1", true),
                new TrelloList("2", "List2", false), new TrelloList("3", "List3", true));

        //When
        List<TrelloList> listResult = trelloMaper.mapToList(list);

        //Then
        Assert.assertEquals(listComparative, listResult);
    }

    @Test
    public void mapToListDtoTest (){

        //Given
        List<TrelloList> list = Arrays.asList(new TrelloList("1", "List1", true),
                new TrelloList("2", "List2", false), new TrelloList("3", "List3", true));
        List<TrelloListDto> listComparative = Arrays.asList(new TrelloListDto("1", "List1", true),
                new TrelloListDto("2", "List2", false), new TrelloListDto("3", "List3", true));

        //When
        List<TrelloListDto> listResult = trelloMaper.mapToListDto(list);

        //Then
        Assert.assertEquals(listComparative,listResult);

    }

    @Test
    public void mapToCardDtoTest (){

        //Given
        TrelloCard trelloCard = new TrelloCard("Card A", "Example description", "last", "3");

        TrelloCardDto compareTrelloCardDto = new TrelloCardDto("Card A", "Example description", "last", "3");

        //When
        TrelloCardDto resultTrelloCard = trelloMaper.mapToCardDto(trelloCard);

        //Then
        Assert.assertEquals(compareTrelloCardDto, resultTrelloCard);
    }

    @Test
    public void mapToCardTest (){

        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card A", "Example description", "top", "3");

        TrelloCard compareTrelloCard = new TrelloCard("Card A", "Example description", "top", "3");
        //When
        TrelloCard resultTrelloCard = trelloMaper.mapToCard(trelloCardDto);

        //Then
        Assert.assertEquals(compareTrelloCard, resultTrelloCard);
    }
}
