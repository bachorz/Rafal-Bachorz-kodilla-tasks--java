package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
    public void createTrelloCardTest() {

        //Given
        TrelloCardDto trelloCardDto= new TrelloCardDto("Card A", "Example description", "last", "3");

        when(adminConfig.getAdminMail()).thenReturn("jakisuzytkownik666@gmail.com");
        when(trelloClient.createNewCard(any())).thenReturn(new CreatedTrelloCardDto());

        //When
        trelloService.createTrelloCard(trelloCardDto);

        //Them
        verify(emailService, times(1)).send(any(Mail.class));
    }
}