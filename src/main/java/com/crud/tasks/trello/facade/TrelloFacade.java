package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValdator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrelloFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloFacade.class);

    @Autowired
    private TrelloService trelloService;

    @Autowired
    private TrelloMapper trelloMapper;

    @Autowired
    private TrelloValdator trelloValdator;

    public List<TrelloBoardDto> fetchTrelloBoards(){

        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloService.fetchTrelloBoards());
        List<TrelloBoard> filteredBoards = trelloValdator.validateTrelloBoards(trelloBoards);
        return trelloMapper.mapToBoardsDto(filteredBoards);
    }

    public CreatedTrelloCardDto createCard (final TrelloCardDto trelloCardDto){
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        trelloValdator.validateCard(trelloCard);
        return trelloService.createTrelloCard(trelloMapper.mapToCardDto(trelloCard));
    }
}