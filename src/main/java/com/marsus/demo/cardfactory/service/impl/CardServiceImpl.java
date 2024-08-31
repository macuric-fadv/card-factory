package com.marsus.demo.cardfactory.service.impl;

import com.marsus.demo.cardfactory.exception.NotFoundException;
import com.marsus.demo.cardfactory.model.dto.CardRequestDto;
import com.marsus.demo.cardfactory.model.dto.ClientInfoDto;
import com.marsus.demo.cardfactory.model.dto.NewCardRequestDto;
import com.marsus.demo.cardfactory.model.entity.CardRequest;
import com.marsus.demo.cardfactory.model.entity.Client;
import com.marsus.demo.cardfactory.model.Status;
import com.marsus.demo.cardfactory.repository.CardRequestRepository;
import com.marsus.demo.cardfactory.repository.ClientRepository;
import com.marsus.demo.cardfactory.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.marsus.demo.cardfactory.model.mapper.ClientMapper.*;

@Service
public class CardServiceImpl implements CardService {

    final Logger log = LoggerFactory.getLogger(CardServiceImpl.class);

    private final ClientRepository clientRepository;

    private final CardRequestRepository cardRequestRepository;

    public CardServiceImpl(final ClientRepository clientRepository, final CardRequestRepository cardRequestRepository) {
        this.clientRepository = clientRepository;
        this.cardRequestRepository = cardRequestRepository;
    }

    @Override
    public ClientInfoDto createCardRequest(final NewCardRequestDto newCardRequest) {

        if (newCardRequest == null || newCardRequest.getClient() == null) {
            throw new IllegalArgumentException("New card request client data not provided");
        }

        log.info("createCardRequest: creating new card request, client OIB: {}", newCardRequest.getClient().getOib());
        Optional<Client> clientOptional = clientRepository.findByOib(newCardRequest.getClient().getOib());
        Client client = clientOptional.orElse(mapToClient(newCardRequest));
        CardRequest cardRequest = CardRequest.builder()
                .status(Status.NEW)
                .client(client)
                .build();
        client.addCardRequest(cardRequest);
        clientRepository.save(client);
        log.info("createCardRequest: new card request created, client OIB: {}", client.getOib());
        return mapToClientInfoDto(client);
    }

    @Override
    public ClientInfoDto updateCardRequest(final CardRequestDto cardRequestDto) throws NotFoundException {

        if (cardRequestDto == null) {
            throw new IllegalArgumentException("Card request not provided");
        }
        if (cardRequestDto.getClientId() == null) {
            throw new IllegalArgumentException("Card request client ID not provided");
        }
        if (cardRequestDto.getRequest() == null || cardRequestDto.getRequest().getId() == null) {
            throw new IllegalArgumentException("Card request ID not provided");
        }

        log.info("updateCardRequest: updating card request for client, client ID: {}, request ID: {}",
                cardRequestDto.getClientId(), cardRequestDto.getRequest().getId());

        Optional<Client> clientOptional = clientRepository.findById(cardRequestDto.getClientId());
        if (clientOptional.isEmpty()) {
            throw new NotFoundException("Client not found for ID " + cardRequestDto.getClientId());
        }
        Client client = clientOptional.get();
        log.debug("updateCardRequest: client found for ID: {}", client.getId());

        Optional<CardRequest> requestOptional = client.getCardRequests().stream()
                .filter(cardRequest -> cardRequest.getId().equals(cardRequestDto.getRequest().getId())).findAny();
        if (requestOptional.isEmpty()) {
            throw new NotFoundException("Client request not found for ID " + cardRequestDto.getRequest().getId());
        }
        CardRequest cardRequest = requestOptional.get();
        log.debug("updateCardRequest: card request found for ID: {}", cardRequestDto.getRequest().getId());

        cardRequest.setStatus(cardRequestDto.getRequest().getStatus());
        cardRequestRepository.save(cardRequest);

        log.info("updateCardRequest: card request updated, client ID: {}, request ID: {}",
                client.getId(), cardRequest.getId());

        return mapToClientInfoDto(client);
    }

    @Override
    public ClientInfoDto getClientInfo(final String oib) {
        return null;
    }

    @Override
    public void deleteClient(final String oib) {

    }
}
