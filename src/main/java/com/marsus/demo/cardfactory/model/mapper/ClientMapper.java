package com.marsus.demo.cardfactory.model.mapper;

import com.marsus.demo.cardfactory.model.dto.ClientDto;
import com.marsus.demo.cardfactory.model.dto.ClientInfoDto;
import com.marsus.demo.cardfactory.model.dto.NewCardRequestDto;
import com.marsus.demo.cardfactory.model.dto.RequestDto;
import com.marsus.demo.cardfactory.model.entity.Client;

import java.util.List;

public class ClientMapper {

    public static Client mapToClient(final NewCardRequestDto newCardRequest) {

        if (newCardRequest == null || newCardRequest.getClient() == null) {
            return null;
        }
        return Client.builder()
                .firstName(newCardRequest.getClient().getFirstName())
                .lastName(newCardRequest.getClient().getLastName())
                .oib(newCardRequest.getClient().getOib())
                .build();
    }

    public static ClientInfoDto mapToClientInfoDto(final Client client) {

        if (client == null) {
            return null;
        }

        ClientDto clientDto = ClientDto.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .oib(client.getOib())
                .build();
        List<RequestDto> requestDtoList = client.getCardRequests().stream()
                .map(CardRequestMapper::mapToRequestDto)
                .toList();

        return ClientInfoDto.builder()
                .client(clientDto)
                .requests(requestDtoList)
                .build();
    }
}
