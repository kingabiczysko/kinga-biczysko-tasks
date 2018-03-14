package com.crud.tasks.service;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.config.AdminConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig adminConfig;



    @Test
    public void createTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("trello_name",
                "trello_descr", "top", "1" );
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("id_1", "trello_name", "http://test.com");

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);


        //When
        CreatedTrelloCardDto resultTrelloCardDto = trelloService.createTrelloCard(trelloCardDto);

        //Then
        Assert.assertEquals("trello_name", resultTrelloCardDto.getName());
    }

    @Test
    public void fetchTrelloBoards(){
        //Given
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        List<TrelloListDto> trelloDtoLists = new ArrayList<>();
        trelloDtoLists.add(new TrelloListDto("1", "trello_list_Kodilla", false));
        trelloDtoLists.add(new TrelloListDto("2","trello_list_priv", false));
        trelloBoardDtoList.add(new TrelloBoardDto("1", "trello_board", trelloDtoLists));
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoList);

        //When
        List<TrelloBoardDto> resultBoardDtoList = trelloService.fetchTrelloBoards();

        //Then
        Assert.assertEquals("trello_board", resultBoardDtoList.get(0).getName());
        resultBoardDtoList.forEach(trelloBoardDto -> {
            Assert.assertEquals("1",trelloBoardDto.getLists().get(0).getId());
            Assert.assertFalse(trelloBoardDto.getLists().get(1).isClosed());
        });
    }
}