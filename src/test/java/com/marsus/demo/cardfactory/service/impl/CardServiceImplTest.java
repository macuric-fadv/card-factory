package com.marsus.demo.cardfactory.service.impl;

import com.marsus.demo.cardfactory.exception.NotFoundException;
import com.marsus.demo.cardfactory.model.Status;
import com.marsus.demo.cardfactory.model.dto.*;
import com.marsus.demo.cardfactory.model.entity.CardRequest;
import com.marsus.demo.cardfactory.model.entity.Client;
import com.marsus.demo.cardfactory.repository.CardRequestRepository;
import com.marsus.demo.cardfactory.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CardServiceImplTest {

    @InjectMocks
    private CardServiceImpl cardService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private CardRequestRepository cardRequestRepository;

    @Test
    public void testCreateCardRequest_newClient() {

        doAnswer(invocation -> {
            Client client = invocation.getArgument(0);
            Long id = 1L;
            client.setId(id);
            int last = client.getCardRequests().size() - 1;
            CardRequest cardRequest = client.getCardRequests().get(last);
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

    @Test
    public void testCreateCardRequest_existingClient_no_requests() {

        doAnswer(invocation -> {
            Client client = invocation.getArgument(0);
            int last = client.getCardRequests().size() - 1;
            CardRequest cardRequest = client.getCardRequests().get(last);
            cardRequest.setId(2L);
            return client;
        }).when(clientRepository).save(any(Client.class));


        Client client = Client.builder()
                .id(1L)
                .firstName("Jure")
                .lastName("Radić")
                .oib("14131362243")
                .build();
        when(clientRepository.findByOib(anyString())).thenReturn(Optional.of(client));

        ClientDto clientDto = ClientDto.builder()
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .oib(client.getOib())
                .build();
        NewCardRequestDto newCardRequestDto = NewCardRequestDto.builder()
                .client(clientDto)
                .build();
        ClientInfoDto clientInfo = cardService.createCardRequest(newCardRequestDto);
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

    @Test
    public void testCreateCardRequest_existingClient_existing_requests() {

        doAnswer(invocation -> {
            Client client = invocation.getArgument(0);
            int last = client.getCardRequests().size() - 1;
            CardRequest cardRequest = client.getCardRequests().get(last);
            cardRequest.setId(last + 1L);
            return client;
        }).when(clientRepository).save(any(Client.class));


        CardRequest cardRequest = CardRequest.builder()
                .id(1L)
                .status(Status.COMPLETED)
                .completedDate(LocalDateTime.now().minusDays(20))
                .build();
        List<CardRequest> cardRequests = new ArrayList<>();
        cardRequests.add(cardRequest);
        Client client = Client.builder()
                .id(1L)
                .firstName("Jure")
                .lastName("Radić")
                .oib("14131362243")
                .cardRequests(cardRequests)
                .build();
        cardRequest.setClient(client);
        when(clientRepository.findByOib(anyString())).thenReturn(Optional.of(client));

        ClientDto clientDto = ClientDto.builder()
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .oib(client.getOib())
                .build();
        NewCardRequestDto newCardRequestDto = NewCardRequestDto.builder()
                .client(clientDto)
                .build();
        ClientInfoDto clientInfo = cardService.createCardRequest(newCardRequestDto);
        assertNotNull(clientInfo);
        assertNotNull(clientInfo.getClient());
        assertNotNull(clientInfo.getClient().getId());
        assertEquals(client.getFirstName(), clientInfo.getClient().getFirstName());
        assertEquals(client.getLastName(), clientInfo.getClient().getLastName());
        assertEquals(client.getOib(), clientInfo.getClient().getOib());
        assertNotNull(clientInfo.getRequests());
        assertEquals(2, clientInfo.getRequests().size());
        RequestDto requestDto = clientInfo.getRequests().get(1);
        assertNotNull(requestDto);
        assertNotNull(requestDto.getId());
        assertEquals(Status.NEW, requestDto.getStatus());
    }

    @Test
    public void testCreateCardRequest_nullNewCardRequest() {

        assertThrows(IllegalArgumentException.class, () -> cardService.createCardRequest(null));
    }

    @Test
    public void testCreateCardRequest_nullClient() {

        NewCardRequestDto newCardRequestDto = new NewCardRequestDto(null);
        assertThrows(IllegalArgumentException.class, () -> cardService.createCardRequest(newCardRequestDto));
    }

    @Test
    public void testUpdateCardRequest() throws NotFoundException {

        CardRequest cardRequest = CardRequest.builder()
                .id(1L)
                .status(Status.APPROVED)
                .build();
        List<CardRequest> cardRequests = new ArrayList<>();
        cardRequests.add(cardRequest);
        Client client = Client.builder()
                .id(1L)
                .firstName("Jure")
                .lastName("Radić")
                .oib("14131362243")
                .cardRequests(cardRequests)
                .build();
        cardRequest.setClient(client);
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(client));

        RequestDto requestDto = RequestDto.builder()
                .id(1L)
                .status(Status.PENDING)
                .build();
        CardRequestDto cardRequestDto = CardRequestDto.builder()
                .clientId(1L)
                .request(requestDto)
                .build();
        ClientInfoDto clientInfo = cardService.updateCardRequest(cardRequestDto);
        assertNotNull(clientInfo);
        assertNotNull(clientInfo.getClient());
        assertEquals(cardRequestDto.getClientId(), clientInfo.getClient().getId());
        assertNotNull(clientInfo.getRequests());
        assertEquals(1, clientInfo.getRequests().size());
        RequestDto request = clientInfo.getRequests().get(0);
        assertNotNull(request);
        assertEquals(requestDto.getId(), request.getId());
        assertEquals(requestDto.getStatus(), request.getStatus());
    }

    @Test
    public void testUpdateCardRequest_cardRequestDtoNull() {

        assertThrows(IllegalArgumentException.class, () -> cardService.updateCardRequest(null));
    }

    @Test
    public void testUpdateCardRequest_clientIdNull() {

        CardRequestDto cardRequestDto = new CardRequestDto(null, null);
        assertThrows(IllegalArgumentException.class, () -> cardService.updateCardRequest(null));
    }

    @Test
    public void testUpdateCardRequest_requestNull() {

        CardRequestDto cardRequestDto = new CardRequestDto(1L, null);
        assertThrows(IllegalArgumentException.class, () -> cardService.updateCardRequest(null));
    }

    @Test
    public void testUpdateCardRequest_requestIdNull() {

        RequestDto requestDto = new RequestDto(null, null);
        CardRequestDto cardRequestDto = new CardRequestDto(1L, requestDto);
        assertThrows(IllegalArgumentException.class, () -> cardService.updateCardRequest(null));
    }

    @Test
    public void testUpdateCardRequest_clientNotFound() {

        RequestDto requestDto = RequestDto.builder()
                .id(1L)
                .status(Status.PENDING)
                .build();
        CardRequestDto cardRequestDto = CardRequestDto.builder()
                .clientId(1L)
                .request(requestDto)
                .build();
        assertThrows(NotFoundException.class, () -> cardService.updateCardRequest(cardRequestDto));

    }

    @Test
    public void testUpdateCardRequest_requestNotFound() {

        Client client = Client.builder()
                .id(1L)
                .firstName("Jure")
                .lastName("Radić")
                .oib("14131362243")
                .build();
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(client));

        RequestDto requestDto = RequestDto.builder()
                .id(1L)
                .status(Status.PENDING)
                .build();
        CardRequestDto cardRequestDto = CardRequestDto.builder()
                .clientId(1L)
                .request(requestDto)
                .build();
        assertThrows(NotFoundException.class, () -> cardService.updateCardRequest(cardRequestDto));
    }
}
