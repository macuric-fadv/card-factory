package com.marsus.demo.cardfactory.service;

import com.marsus.demo.cardfactory.model.dto.ClientInfoDto;
import com.marsus.demo.cardfactory.model.dto.CardRequestDto;
import com.marsus.demo.cardfactory.model.dto.NewCardRequestDto;

public interface CardService {

    ClientInfoDto createCardRequest(NewCardRequestDto newCardRequest);

    void updateCardRequest(CardRequestDto cardRequest);

    ClientInfoDto getClientInfo(String oib);

    void deleteClient(String oib);
}
