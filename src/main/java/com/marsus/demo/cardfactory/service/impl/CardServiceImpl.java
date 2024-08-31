package com.marsus.demo.cardfactory.service.impl;

import com.marsus.demo.cardfactory.model.dto.CardRequestDto;
import com.marsus.demo.cardfactory.model.dto.ClientInfoDto;
import com.marsus.demo.cardfactory.model.dto.NewCardRequestDto;
import com.marsus.demo.cardfactory.model.entity.CardRequest;
import com.marsus.demo.cardfactory.model.entity.Client;
import com.marsus.demo.cardfactory.model.Status;
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

    public CardServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public void createCardRequest(final NewCardRequestDto newCardRequest) {

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
        log.info("createCardRequest: new card request created, client OID: {}", client.getOib());
    }

    @Override
    public void updateCardRequest(final CardRequestDto cardRequest) {

    }

    @Override
    public ClientInfoDto getClientInfo(final String oib) {
        return null;
    }

    @Override
    public void deleteClient(final String oib) {

    }
}
