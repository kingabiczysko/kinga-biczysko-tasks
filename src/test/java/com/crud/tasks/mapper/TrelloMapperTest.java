package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TrelloMapperTest {

    TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void mapToBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "1list", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("2","2list", false);
        trelloListDto.add(trelloListDto1);
        trelloListDto.add(trelloListDto2);
        trelloBoardsDto.add(new TrelloBoardDto("test_id", "test_board", trelloListDto));

        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);

        //Then
        Assert.assertEquals("test_board", trelloBoards.get(0).getName());
        Assert.assertTrue( trelloBoards.get(0).getLists().get(0).isClosed());

    }

    @Test
    public void mapToBoardsDto() {
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("Trello_board_id", "Trello_board_name", trelloLists));

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        Assert.assertEquals("Trello_board_name", trelloBoardDtos.get(0).getName());
        Assert.assertEquals(trelloLists, trelloBoardDtos.get(0).getLists());
    }

    @Test
    public void mapToList() {
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("List1_id", "List1_name", false ));
        //When

        List<TrelloList>trelloList1 = trelloMapper.mapToList(trelloListDtos);

        //Then
        Assert.assertEquals("List1_name", trelloList1.get(0).getName());
        Assert.assertFalse(trelloList1.get(0).isClosed());

    }

    @Test
    public void mapToListDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("List2_id", "List2_name", true));
        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);

        //Then
        Assert.assertTrue(trelloListDtos.get(0).isClosed());
        Assert.assertEquals("List2_id", trelloListDtos.get(0).getId());
    }

    @Test
    public void mapToCardDto() {

        //Given
        TrelloCard trelloCard = new TrelloCard(
                "Test task1",
                "Test Description1",
                "top",
                "test_id1");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        Assert.assertEquals("Test Description1", trelloCardDto.getDescription());
        Assert.assertEquals("test_id1", trelloCardDto.getListId());
    }

    @Test
    public void mapToCard() {

        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Test task2",
                "Test Description2",
                "top",
                "test_id2");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        Assert.assertEquals("Test task2", trelloCard.getName());
        Assert.assertEquals("top", trelloCard.getPos());

    }
}