package com.marsus.demo.cardfactory.service.impl;

import com.marsus.demo.cardfactory.exception.NotFoundException;
import com.marsus.demo.cardfactory.model.UpdateCardRequest;
import com.marsus.demo.cardfactory.model.ClientInfo;
import com.marsus.demo.cardfactory.model.NewCardRequest;
import com.marsus.demo.cardfactory.dao.entity.CardRequestEntity;
import com.marsus.demo.cardfactory.dao.entity.ClientEntity;
import com.marsus.demo.cardfactory.model.Status;
import com.marsus.demo.cardfactory.dao.repository.CardRequestRepository;
import com.marsus.demo.cardfactory.dao.repository.ClientRepository;
import com.marsus.demo.cardfactory.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.marsus.demo.cardfactory.model.mapper.ClientMapper.*;

/**
 * {@inheritDoc}
 */
@Slf4j
@Service
public class CardServiceImpl implements CardService {

    private final ClientRepository clientRepository;

    private final CardRequestRepository cardRequestRepository;

    public CardServiceImpl(final ClientRepository clientRepository, final CardRequestRepository cardRequestRepository) {
        this.clientRepository = clientRepository;
        this.cardRequestRepository = cardRequestRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClientInfo createCardRequest(final NewCardRequest newCardRequest) {

        if (newCardRequest == null) {
            throw new IllegalArgumentException("New card request client data not provided");
        }

        log.info("createCardRequest: creating new card request, client OIB: {}", newCardRequest.getOib());
        Optional<ClientEntity> clientOptional = clientRepository.findByOib(newCardRequest.getOib());
        ClientEntity client = clientOptional.orElse(mapToClientEntity(newCardRequest));
        CardRequestEntity cardRequest = CardRequestEntity.builder()
                .status(Status.NEW)
                .client(client)
                .build();
        client.addCardRequest(cardRequest);
        clientRepository.save(client);
        log.info("createCardRequest: new card request created, client OIB: {}", client.getOib());
        return mapToClientInfo(client);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClientInfo updateCardRequest(final UpdateCardRequest updateCardRequest) throws NotFoundException {

        if (updateCardRequest == null) {
            throw new IllegalArgumentException("Card request not provided");
        }
        if (updateCardRequest.getClientId() == null) {
            throw new IllegalArgumentException("Card request client ID not provided");
        }
        if (updateCardRequest.getRequest() == null || updateCardRequest.getRequest().getRequestId() == null) {
            throw new IllegalArgumentException("Card request ID not provided");
        }

        log.info("updateCardRequest: updating card request for client, client ID: {}, request ID: {}",
                updateCardRequest.getClientId(), updateCardRequest.getRequest().getRequestId());

        Optional<ClientEntity> clientOptional = clientRepository.findById(updateCardRequest.getClientId());
        if (clientOptional.isEmpty()) {
            throw new NotFoundException("Client not found for ID " + updateCardRequest.getClientId());
        }
        ClientEntity client = clientOptional.get();
        log.debug("updateCardRequest: client found for ID: {}", client.getId());

        Optional<CardRequestEntity> requestOptional = client.getCardRequests().stream()
                .filter(cardRequest -> cardRequest.getId().equals(updateCardRequest.getRequest().getRequestId())).findAny();
        if (requestOptional.isEmpty()) {
            throw new NotFoundException("Client request not found for ID " + updateCardRequest.getRequest().getRequestId());
        }
        CardRequestEntity cardRequest = requestOptional.get();
        log.debug("updateCardRequest: card request found for ID: {}", updateCardRequest.getRequest().getRequestId());

        cardRequest.setStatus(updateCardRequest.getRequest().getStatus());
        cardRequestRepository.save(cardRequest);

        log.info("updateCardRequest: card request updated, client ID: {}, request ID: {}",
                client.getId(), cardRequest.getId());

        return mapToClientInfo(client);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClientInfo getClientInfo(final String oib) throws NotFoundException {

        if (oib == null) {
            throw new IllegalArgumentException("OIB not provided");
        }

        log.info("getClientInfo: retrieving client by OIB: {}", oib);

        Optional<ClientEntity> clientOptional = clientRepository.findByOib(oib);
        if (clientOptional.isEmpty()) {
            throw new NotFoundException("Client not found by OIB: " + oib);
        }

        ClientEntity client = clientOptional.get();
        log.info("getClientInfo: client found for OIB: {}, client ID: {}", client.getOib(), client.getId());
        return mapToClientInfo(client);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteClient(final String oib) throws NotFoundException {

        if (oib == null) {
            throw new IllegalArgumentException("OIB not provided");
        }

        log.info("deleteClient: deleting client for OIB: {}", oib);

        Optional<ClientEntity> clientOptional = clientRepository.findByOib(oib);
        if (clientOptional.isEmpty()) {
            throw new NotFoundException("Client not found for OIB " + oib);
        }

        clientRepository.delete(clientOptional.get());

        log.info("deleteClient: client deleted for OIB: {}", oib);
    }
}
