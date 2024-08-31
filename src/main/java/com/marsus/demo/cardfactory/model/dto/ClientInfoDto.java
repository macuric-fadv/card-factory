package com.marsus.demo.cardfactory.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
public class ClientInfoDto {

    private ClientDto client;

    private List<RequestDto> requests;
}
