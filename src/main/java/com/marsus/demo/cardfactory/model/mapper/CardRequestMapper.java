package com.marsus.demo.cardfactory.model.mapper;

import com.marsus.demo.cardfactory.model.dto.RequestDto;
import com.marsus.demo.cardfactory.model.entity.CardRequest;

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
}
