package com.marsus.demo.cardfactory.service.impl;

import com.marsus.demo.cardfactory.dto.CardRequestDto;
import com.marsus.demo.cardfactory.dto.ClientInfoDto;
import com.marsus.demo.cardfactory.dto.NewCardRequestDto;
import com.marsus.demo.cardfactory.repository.ClientRepository;
import com.marsus.demo.cardfactory.service.CardService;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    private final ClientRepository clientRepository;

    public CardServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public void createCardRequest(final NewCardRequestDto newCardRequest) {

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
