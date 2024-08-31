package com.marsus.demo.cardfactory.service;

import com.marsus.demo.cardfactory.exception.NotFoundException;
import com.marsus.demo.cardfactory.model.dto.ClientInfoDto;
import com.marsus.demo.cardfactory.model.dto.CardRequestDto;
import com.marsus.demo.cardfactory.model.dto.NewCardRequestDto;

public interface CardService {

    ClientInfoDto createCardRequest(NewCardRequestDto newCardRequest);

    ClientInfoDto updateCardRequest(CardRequestDto cardRequest) throws NotFoundException;

    ClientInfoDto getClientInfo(String oib) throws NotFoundException;

    void deleteClient(String oib);
}
