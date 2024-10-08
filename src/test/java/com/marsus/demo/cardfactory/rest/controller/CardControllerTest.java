package com.marsus.demo.cardfactory.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marsus.demo.cardfactory.exception.NotFoundException;
import com.marsus.demo.cardfactory.model.*;
import com.marsus.demo.cardfactory.service.CardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest
@ExtendWith(MockitoExtension.class)
public class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardService cardService;

    @Test
    public void testPostNewCardRequest() throws Exception {

        CardRequest request = CardRequest.builder()
                .requestId(1L)
                .status(Status.NEW)
                .build();
        ClientInfo clientInfo = ClientInfo.builder()
                .clientId(1L)
                .firstName("Mario")
                .lastName("Juras")
                .oib("14131362243")
                .requests(List.of(request))
                .build();
        when(cardService.createCardRequest(ArgumentMatchers.any(NewCardRequest.class))).thenReturn(clientInfo);

        NewCardRequest newCardRequest = NewCardRequest.builder()
                .firstName("Mario")
                .lastName("Juras")
                .oib("14131362243")
                .build();
        mockMvc.perform(post("/api/v1/card-request")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(new ObjectMapper().writeValueAsString(newCardRequest))
                ).andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", containsString(String.valueOf(clientInfo.getClientId()))));

        verify(cardService, times(1)).createCardRequest(ArgumentMatchers.any(NewCardRequest.class));
    }

    @Test
    public void testPostNewCardRequest_noBody() throws Exception {

        CardRequest request = CardRequest.builder()
                .requestId(1L)
                .status(Status.NEW)
                .build();
        ClientInfo clientInfo = ClientInfo.builder()
                .clientId(1L)
                .firstName("Mario")
                .lastName("Šimić")
                .oib("14131362243")
                .requests(List.of(request))
                .build();
        when(cardService.createCardRequest(ArgumentMatchers.any(NewCardRequest.class))).thenReturn(clientInfo);

        mockMvc.perform(post("/api/v1/card-request")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content("")
                ).andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateCardRequest() throws Exception {

        CardRequest request = CardRequest.builder()
                .requestId(1L)
                .status(Status.PENDING)
                .build();
        ClientInfo clientInfo = ClientInfo.builder()
                .clientId(1L)
                .firstName("Mario")
                .lastName("Šimić")
                .oib("14131362243")
                .requests(List.of(request))
                .build();
        when(cardService.updateCardRequest(ArgumentMatchers.any(UpdateCardRequest.class))).thenReturn(clientInfo);

        com.marsus.demo.cardfactory.rest.model.UpdateCardRequest updateCardRequest = com.marsus.demo.cardfactory.rest.model.UpdateCardRequest.builder()
                .status("PENDING")
                .build();
        mockMvc.perform(put("/api/v1/card-request/{clientId}/request/{requestId}",
                        clientInfo.getClientId(), request.getRequestId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(new ObjectMapper().writeValueAsString(updateCardRequest))
                ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.clientId").value(1L))
                .andExpect(jsonPath("$.firstName").value("Mario"))
                .andExpect(jsonPath("$.lastName").value("Šimić"))
                .andExpect(jsonPath("$.oib").value("14131362243"))
                .andExpect(jsonPath("$.requests", hasSize(1)))
                .andExpect(jsonPath("$.requests[0].requestId").value(1L))
                .andExpect(jsonPath("$.requests[0].status").value("PENDING"));

        verify(cardService, times(1)).updateCardRequest(ArgumentMatchers.any(UpdateCardRequest.class));
    }

    @Test
    public void testUpdateCardRequest_nullClientId() throws Exception {

        com.marsus.demo.cardfactory.rest.model.UpdateCardRequest updateCardRequest = com.marsus.demo.cardfactory.rest.model.UpdateCardRequest.builder()
                .status("PENDING")
                .build();
        mockMvc.perform(put("/api/v1/card-request/{clientId}/request/{requestId}",
                        null, 1L)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(new ObjectMapper().writeValueAsString(updateCardRequest))
                ).andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateCardRequest_nullRequestId() throws Exception {

        com.marsus.demo.cardfactory.rest.model.UpdateCardRequest updateCardRequest = com.marsus.demo.cardfactory.rest.model.UpdateCardRequest.builder()
                .status("PENDING")
                .build();
        mockMvc.perform(put("/api/v1/card-request/{clientId}/request/{requestId}",
                1L, null)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding(StandardCharsets.UTF_8)
                .content(new ObjectMapper().writeValueAsString(updateCardRequest))
        ).andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateCardRequest_noBody() throws Exception {

        mockMvc.perform(put("/api/v1/card-request/{clientId}/request/{requestId}",
                1L, 1L)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding(StandardCharsets.UTF_8)
                .content("")
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateCardRequest_clientNotFound() throws Exception {

        when(cardService.updateCardRequest(ArgumentMatchers.any(UpdateCardRequest.class)))
                .thenThrow(new NotFoundException("Client not found"));

        com.marsus.demo.cardfactory.rest.model.UpdateCardRequest updateCardRequest = com.marsus.demo.cardfactory.rest.model.UpdateCardRequest.builder()
                .status("PENDING")
                .build();
        mockMvc.perform(put("/api/v1/card-request/{clientId}/request/{requestId}", 1L, 1L)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(new ObjectMapper().writeValueAsString(updateCardRequest))
                ).andExpect(status().isNotFound());

        verify(cardService, times(1)).updateCardRequest(ArgumentMatchers.any(UpdateCardRequest.class));
    }

    @Test
    public void testGetClientInfo() throws Exception {

        final String oib = "14131362243";
        CardRequest request = CardRequest.builder()
                .requestId(1L)
                .status(Status.PENDING)
                .build();
        ClientInfo clientInfo = ClientInfo.builder()
                .clientId(1L)
                .firstName("Mario")
                .lastName("Šimić")
                .oib(oib)
                .requests(List.of(request))
                .build();
        when(cardService.getClientInfo(ArgumentMatchers.anyString())).thenReturn(clientInfo);

        mockMvc.perform(get("/api/v1/card-request")
                        .param("oib", oib)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding(StandardCharsets.UTF_8)
                ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.clientId").value(1L))
                .andExpect(jsonPath("$.firstName").value("Mario"))
                .andExpect(jsonPath("$.lastName").value("Šimić"))
                .andExpect(jsonPath("$.oib").value("14131362243"))
                .andExpect(jsonPath("$.requests", hasSize(1)))
                .andExpect(jsonPath("$.requests[0].requestId").value(1L))
                .andExpect(jsonPath("$.requests[0].status").value("PENDING"));

        verify(cardService, times(1)).getClientInfo(ArgumentMatchers.anyString());
    }

    @Test
    public void testGetClientInfo_emptyOib() throws Exception {

        final String oib = "14131362243";
        CardRequest request = CardRequest.builder()
                .requestId(1L)
                .status(Status.PENDING)
                .build();
        ClientInfo clientInfo = ClientInfo.builder()
                .clientId(1L)
                .firstName("Mario")
                .lastName("Šimić")
                .oib(oib)
                .requests(List.of(request))
                .build();
        when(cardService.getClientInfo(ArgumentMatchers.anyString())).thenReturn(clientInfo);

        mockMvc.perform(get("/api/v1/card-request")
                        .param("oib", "")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding(StandardCharsets.UTF_8)
                ).andExpect(status().isBadRequest());
    }

    @Test
    public void testGetClientInfo_invalidOib() throws Exception {

        final String oib = "14131362243";
        CardRequest request = CardRequest.builder()
                .requestId(1L)
                .status(Status.PENDING)
                .build();
        ClientInfo clientInfo = ClientInfo.builder()
                .clientId(1L)
                .firstName("Mario")
                .lastName("Šimić")
                .oib(oib)
                .requests(List.of(request))
                .build();
        when(cardService.getClientInfo(ArgumentMatchers.anyString())).thenReturn(clientInfo);

        mockMvc.perform(get("/api/v1/card-request")
                .param("oib", "111")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding(StandardCharsets.UTF_8)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void testGetClientInfo_notFound() throws Exception {

        when(cardService.getClientInfo(ArgumentMatchers.anyString()))
                .thenThrow(new NotFoundException("Client not found"));

        mockMvc.perform(get("/api/v1/card-request")
                .param("oib", "14131362243")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding(StandardCharsets.UTF_8)
        ).andExpect(status().isNotFound());

        verify(cardService, times(1)).getClientInfo(ArgumentMatchers.anyString());
    }

    @Test
    public void testDeleteClient() throws Exception {

        doNothing().when(cardService).deleteClient(ArgumentMatchers.anyString());

        mockMvc.perform(delete("/api/v1/card-request")
                        .param("oib", "14131362243")
                ).andExpect(status().isNoContent());

        verify(cardService, times(1)).deleteClient(ArgumentMatchers.anyString());
    }

    @Test
    public void testDeleteClient_emptyOib() throws Exception {

        doNothing().when(cardService).deleteClient(ArgumentMatchers.anyString());

        mockMvc.perform(delete("/api/v1/card-request")
                .param("oib", "")
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteClient_invalidOib() throws Exception {

        doNothing().when(cardService).deleteClient(ArgumentMatchers.anyString());

        mockMvc.perform(delete("/api/v1/card-request")
                .param("oib", "111")
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteClient_notFound() throws Exception {

        doThrow(new NotFoundException("Client not found"))
                .when(cardService).deleteClient(ArgumentMatchers.anyString());

        mockMvc.perform(delete("/api/v1/card-request")
                .param("oib", "14131362243")
        ).andExpect(status().isNotFound());

        verify(cardService, times(1)).deleteClient(ArgumentMatchers.anyString());
    }
}
