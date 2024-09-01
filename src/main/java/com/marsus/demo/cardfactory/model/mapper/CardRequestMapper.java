package com.marsus.demo.cardfactory.model.mapper;

import com.marsus.demo.cardfactory.model.Status;
import com.marsus.demo.cardfactory.model.dto.CardRequestDto;
import com.marsus.demo.cardfactory.model.dto.ClientDto;
import com.marsus.demo.cardfactory.model.dto.NewCardRequestDto;
import com.marsus.demo.cardfactory.model.dto.RequestDto;
import com.marsus.demo.cardfactory.model.entity.CardRequest;
import com.marsus.demo.cardfactory.rest.model.NewCardRequest;
import com.marsus.demo.cardfactory.rest.model.UpdateCardRequest;

import java.util.UUID;

public class CardRequestMapper {

    public static RequestDto mapToRequestDto(CardRequest cardRequest) {

        if (cardRequest == null) {
            return null;
        }

        return RequestDto.builder()
                .id(cardRequest.getId())
                .status(cardRequest.getStatus())
                .build();
    }

    public static NewCardRequestDto mapToNewCardRequestDto(NewCardRequest newCardRequest) {

        if (newCardRequest == null) {
            return null;
        }

        ClientDto clientDto = ClientDto.builder()
                .firstName(newCardRequest.getFirstName())
                .lastName(newCardRequest.getLastName())
                .oib(newCardRequest.getOib())
                .build();
        return NewCardRequestDto.builder()
                .client(clientDto)
                .build();
    }

    public static CardRequestDto mapToCardRequestDto(Long clientId, Long requestId, UpdateCardRequest updateCardRequest) {

        if (clientId == null || requestId == null || updateCardRequest == null) {
            return null;
        }

        RequestDto requestDto = RequestDto.builder()
                .id(requestId)
                .status(Status.valueOf(updateCardRequest.getStatus()))
                .build();
        return CardRequestDto.builder()
                .clientId(clientId)
                .request(requestDto)
                .build();
    }
}
