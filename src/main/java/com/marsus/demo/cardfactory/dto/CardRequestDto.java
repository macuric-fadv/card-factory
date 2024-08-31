package com.marsus.demo.cardfactory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
public class CardRequestDto {

    private String clientOib;

    private RequestDto request;
}
