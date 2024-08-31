package com.marsus.demo.cardfactory.service;

import com.marsus.demo.cardfactory.dto.ClientInfoDto;
import com.marsus.demo.cardfactory.dto.CardRequestDto;
import com.marsus.demo.cardfactory.dto.NewCardRequestDto;

public interface CardService {

    void createCardRequest(NewCardRequestDto newCardRequest);

    void updateCardRequest(CardRequestDto cardRequest);

    ClientInfoDto getClientInfo(String oib);

    void deleteClient(String oib);
}
