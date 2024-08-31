package com.marsus.demo.cardfactory.service.impl;

import com.marsus.demo.cardfactory.model.Status;
import com.marsus.demo.cardfactory.model.dto.ClientDto;
import com.marsus.demo.cardfactory.model.dto.ClientInfoDto;
import com.marsus.demo.cardfactory.model.dto.NewCardRequestDto;
import com.marsus.demo.cardfactory.model.dto.RequestDto;
import com.marsus.demo.cardfactory.model.entity.CardRequest;
import com.marsus.demo.cardfactory.model.entity.Client;
import com.marsus.demo.cardfactory.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CardServiceImplTest {

    @InjectMocks
    private CardServiceImpl cardService;

    @Mock
    private ClientRepository clientRepository;

    @Test
    public void testCreateCardRequest_newClient() {

        doAnswer(invocation -> {
            Client client = invocation.getArgument(0);
            Long id = 1L;
            client.setId(id);
            CardRequest cardRequest = client.getCardRequests().get(0);
            cardRequest.setId(2L);
            return client;
        }).when(clientRepository).save(any(Client.class));

        ClientDto client = ClientDto.builder()
                .firstName("Jure")
                .lastName("Radić")
                .oib("14131362243")
                .build();
        NewCardRequestDto newCardRequest = NewCardRequestDto.builder()
                .client(client)
                .build();

        ClientInfoDto clientInfo = cardService.createCardRequest(newCardRequest);
        assertNotNull(clientInfo);
        assertNotNull(clientInfo.getClient());
        assertNotNull(clientInfo.getClient().getId());
        assertEquals(client.getFirstName(), clientInfo.getClient().getFirstName());
        assertEquals(client.getLastName(), clientInfo.getClient().getLastName());
        assertEquals(client.getOib(), clientInfo.getClient().getOib());
        assertNotNull(clientInfo.getRequests());
        assertEquals(1, clientInfo.getRequests().size());
        RequestDto requestDto = clientInfo.getRequests().get(0);
        assertNotNull(requestDto);
        assertNotNull(requestDto.getId());
        assertEquals(Status.NEW, requestDto.getStatus());
    }
}
