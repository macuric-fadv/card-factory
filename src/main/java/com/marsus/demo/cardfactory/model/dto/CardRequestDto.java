package com.marsus.demo.cardfactory.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
public class CardRequestDto {

    private Long clientId;

    private RequestDto request;
}
