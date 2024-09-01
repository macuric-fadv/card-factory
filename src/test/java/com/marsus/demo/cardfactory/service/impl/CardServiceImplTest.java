package com.marsus.demo.cardfactory.service.impl;

import com.marsus.demo.cardfactory.exception.NotFoundException;
import com.marsus.demo.cardfactory.model.*;
import com.marsus.demo.cardfactory.dao.entity.CardRequestEntity;
import com.marsus.demo.cardfactory.dao.entity.ClientEntity;
import com.marsus.demo.cardfactory.dao.repository.CardRequestRepository;
import com.marsus.demo.cardfactory.dao.repository.ClientRepository;
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
            ClientEntity client = invocation.getArgument(0);
            Long id = 1L;
            client.setId(id);
            int last = client.getCardRequests().size() - 1;
            CardRequestEntity cardRequest = client.getCardRequests().get(last);
            cardRequest.setId(2L);
            return client;
        }).when(clientRepository).save(any(ClientEntity.class));

        NewCardRequest newCardRequest = NewCardRequest.builder()
                .firstName("Jure")
                .lastName("Radić")
                .oib("14131362243")
                .build();

        ClientInfo clientInfo = cardService.createCardRequest(newCardRequest);
        assertNotNull(clientInfo);
        assertNotNull(clientInfo.getClientId());
        assertEquals(newCardRequest.getFirstName(), clientInfo.getFirstName());
        assertEquals(newCardRequest.getLastName(), clientInfo.getLastName());
        assertEquals(newCardRequest.getOib(), clientInfo.getOib());
        assertNotNull(clientInfo.getRequests());
        assertEquals(1, clientInfo.getRequests().size());
        CardRequest request = clientInfo.getRequests().get(0);
        assertNotNull(request);
        assertNotNull(request.getRequestId());
        assertEquals(Status.NEW, request.getStatus());
    }

    @Test
    public void testCreateCardRequest_existingClient_no_requests() {

        doAnswer(invocation -> {
            ClientEntity client = invocation.getArgument(0);
            int last = client.getCardRequests().size() - 1;
            CardRequestEntity cardRequest = client.getCardRequests().get(last);
            cardRequest.setId(2L);
            return client;
        }).when(clientRepository).save(any(ClientEntity.class));


        ClientEntity client = ClientEntity.builder()
                .id(1L)
                .firstName("Jure")
                .lastName("Radić")
                .oib("14131362243")
                .build();
        when(clientRepository.findByOib(anyString())).thenReturn(Optional.of(client));

        NewCardRequest newCardRequestDto = NewCardRequest.builder()
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .oib(client.getOib())
                .build();
        ClientInfo clientInfo = cardService.createCardRequest(newCardRequestDto);
        assertNotNull(clientInfo);
        assertNotNull(clientInfo.getClientId());
        assertEquals(client.getFirstName(), clientInfo.getFirstName());
        assertEquals(client.getLastName(), clientInfo.getLastName());
        assertEquals(client.getOib(), clientInfo.getOib());
        assertNotNull(clientInfo.getRequests());
        assertEquals(1, clientInfo.getRequests().size());
        CardRequest request = clientInfo.getRequests().get(0);
        assertNotNull(request);
        assertNotNull(request.getRequestId());
        assertEquals(Status.NEW, request.getStatus());
    }

    @Test
    public void testCreateCardRequest_existingClient_existing_requests() {

        doAnswer(invocation -> {
            ClientEntity client = invocation.getArgument(0);
            int last = client.getCardRequests().size() - 1;
            CardRequestEntity cardRequest = client.getCardRequests().get(last);
            cardRequest.setId(last + 1L);
            return client;
        }).when(clientRepository).save(any(ClientEntity.class));


        CardRequestEntity cardRequest = CardRequestEntity.builder()
                .id(1L)
                .status(Status.COMPLETED)
                .completedDate(LocalDateTime.now().minusDays(20))
                .build();
        List<CardRequestEntity> cardRequests = new ArrayList<>();
        cardRequests.add(cardRequest);
        ClientEntity client = ClientEntity.builder()
                .id(1L)
                .firstName("Jure")
                .lastName("Radić")
                .oib("14131362243")
                .cardRequests(cardRequests)
                .build();
        cardRequest.setClient(client);
        when(clientRepository.findByOib(anyString())).thenReturn(Optional.of(client));

        NewCardRequest newCardRequestDto = NewCardRequest.builder()
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .oib(client.getOib())
                .build();
        ClientInfo clientInfo = cardService.createCardRequest(newCardRequestDto);
        assertNotNull(clientInfo);
        assertNotNull(clientInfo.getClientId());
        assertEquals(client.getFirstName(), clientInfo.getFirstName());
        assertEquals(client.getLastName(), clientInfo.getLastName());
        assertEquals(client.getOib(), clientInfo.getOib());
        assertNotNull(clientInfo.getRequests());
        assertEquals(2, clientInfo.getRequests().size());
        CardRequest request = clientInfo.getRequests().get(1);
        assertNotNull(request);
        assertNotNull(request.getRequestId());
        assertEquals(Status.NEW, request.getStatus());
    }

    @Test
    public void testCreateCardRequest_nullNewCardRequest() {

        assertThrows(IllegalArgumentException.class, () -> cardService.createCardRequest(null));
    }

    @Test
    public void testUpdateCardRequest() throws NotFoundException {

        CardRequestEntity cardRequest = CardRequestEntity.builder()
                .id(1L)
                .status(Status.APPROVED)
                .build();
        List<CardRequestEntity> cardRequests = new ArrayList<>();
        cardRequests.add(cardRequest);
        ClientEntity client = ClientEntity.builder()
                .id(1L)
                .firstName("Jure")
                .lastName("Radić")
                .oib("14131362243")
                .cardRequests(cardRequests)
                .build();
        cardRequest.setClient(client);
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(client));

        CardRequest request = CardRequest.builder()
                .requestId(1L)
                .status(Status.PENDING)
                .build();
        UpdateCardRequest cardRequestDto = UpdateCardRequest.builder()
                .clientId(1L)
                .request(request)
                .build();
        ClientInfo clientInfo = cardService.updateCardRequest(cardRequestDto);
        assertNotNull(clientInfo);
        assertEquals(cardRequestDto.getClientId(), clientInfo.getClientId());
        assertNotNull(clientInfo.getRequests());
        assertEquals(1, clientInfo.getRequests().size());
        CardRequest req = clientInfo.getRequests().get(0);
        assertNotNull(req);
        assertEquals(request.getRequestId(), req.getRequestId());
        assertEquals(request.getStatus(), req.getStatus());
    }

    @Test
    public void testUpdateCardRequest_cardRequestDtoNull() {

        assertThrows(IllegalArgumentException.class, () -> cardService.updateCardRequest(null));
    }

    @Test
    public void testUpdateCardRequest_clientIdNull() {

        UpdateCardRequest cardRequestDto = new UpdateCardRequest(null, null);
        assertThrows(IllegalArgumentException.class, () -> cardService.updateCardRequest(null));
    }

    @Test
    public void testUpdateCardRequest_requestNull() {

        UpdateCardRequest cardRequestDto = new UpdateCardRequest(1L, null);
        assertThrows(IllegalArgumentException.class, () -> cardService.updateCardRequest(null));
    }

    @Test
    public void testUpdateCardRequest_requestIdNull() {

        CardRequest request = new CardRequest(null, null);
        UpdateCardRequest cardRequestDto = new UpdateCardRequest(1L, request);
        assertThrows(IllegalArgumentException.class, () -> cardService.updateCardRequest(null));
    }

    @Test
    public void testUpdateCardRequest_clientNotFound() {

        CardRequest request = CardRequest.builder()
                .requestId(1L)
                .status(Status.PENDING)
                .build();
        UpdateCardRequest cardRequestDto = UpdateCardRequest.builder()
                .clientId(1L)
                .request(request)
                .build();
        assertThrows(NotFoundException.class, () -> cardService.updateCardRequest(cardRequestDto));

    }

    @Test
    public void testUpdateCardRequest_requestNotFound() {

        ClientEntity client = ClientEntity.builder()
                .id(1L)
                .firstName("Jure")
                .lastName("Radić")
                .oib("14131362243")
                .build();
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(client));

        CardRequest requestDto = CardRequest.builder()
                .requestId(1L)
                .status(Status.PENDING)
                .build();
        UpdateCardRequest cardRequestDto = UpdateCardRequest.builder()
                .clientId(1L)
                .request(requestDto)
                .build();
        assertThrows(NotFoundException.class, () -> cardService.updateCardRequest(cardRequestDto));
    }

    @Test
    public void testGetClientInfo() throws NotFoundException {

        final String oib = "14131362243";
        CardRequestEntity cardRequest = CardRequestEntity.builder()
                .id(1L)
                .status(Status.APPROVED)
                .build();
        List<CardRequestEntity> cardRequests = new ArrayList<>();
        cardRequests.add(cardRequest);
        ClientEntity client = ClientEntity.builder()
                .id(1L)
                .firstName("Jure")
                .lastName("Radić")
                .oib(oib)
                .cardRequests(cardRequests)
                .build();
        cardRequest.setClient(client);
        when(clientRepository.findByOib(anyString())).thenReturn(Optional.of(client));

        ClientInfo clientInfo = cardService.getClientInfo(oib);
        assertNotNull(clientInfo);
        assertEquals(client.getId(), clientInfo.getClientId());
        assertEquals(client.getFirstName(), clientInfo.getFirstName());
        assertEquals(client.getLastName(), clientInfo.getLastName());
        assertEquals(oib, clientInfo.getOib());
        assertNotNull(clientInfo.getRequests());
        assertEquals(1, clientInfo.getRequests().size());
        CardRequest request = clientInfo.getRequests().get(0);
        assertNotNull(request);
        assertEquals(cardRequest.getId(), request.getRequestId());
        assertEquals(cardRequest.getStatus(), request.getStatus());
    }

    @Test
    public void testGetClientInfo_oibNull() {

        assertThrows(IllegalArgumentException.class, () -> cardService.getClientInfo(null));
    }

    @Test
    public void testGetClientInfo_clientNotFound() {

        assertThrows(NotFoundException.class, () -> cardService.getClientInfo("14131362243"));
    }

    @Test
    public void testDeleteClient() throws NotFoundException {

        final String oib = "14131362243";
        CardRequestEntity cardRequest = CardRequestEntity.builder()
                .id(1L)
                .status(Status.APPROVED)
                .build();
        List<CardRequestEntity> cardRequests = new ArrayList<>();
        cardRequests.add(cardRequest);
        ClientEntity client = ClientEntity.builder()
                .id(1L)
                .firstName("Jure")
                .lastName("Radić")
                .oib(oib)
                .cardRequests(cardRequests)
                .build();
        cardRequest.setClient(client);
        when(clientRepository.findByOib(anyString())).thenReturn(Optional.of(client));

        cardService.deleteClient("14131362243");
    }

    @Test
    public void testDeleteClient_nullOib() {

        assertThrows(IllegalArgumentException.class, () -> cardService.deleteClient(null));
    }

    @Test
    public void testDeleteClient_clientNotFound() {

        assertThrows(NotFoundException.class, () -> cardService.deleteClient("14131362243"));
    }
}
