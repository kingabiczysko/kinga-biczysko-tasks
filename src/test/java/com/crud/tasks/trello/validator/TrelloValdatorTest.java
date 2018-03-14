package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloValdatorTest {

    @InjectMocks
    private TrelloValdator trelloValdator;



    @Test  //do sprawdzenia z Pawełem
    public void validateTrelloBoardsTest(){

        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "name_new", new ArrayList<>()));
        trelloBoards.add(new TrelloBoard("2", "name_test", new ArrayList<>()));

        //When
        List<TrelloBoard> resultTrelloBoards = trelloValdator.validateTrelloBoards(trelloBoards);

        //Then
        Assert.assertEquals(1, resultTrelloBoards.size());
        Assert.assertEquals("name_new", trelloBoards.get(0).getName());
    }

}