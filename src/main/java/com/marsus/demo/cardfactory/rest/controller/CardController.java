package com.marsus.demo.cardfactory.rest.controller;

import com.marsus.demo.cardfactory.exception.NotFoundException;
import com.marsus.demo.cardfactory.model.ClientInfo;
import com.marsus.demo.cardfactory.model.NewCardRequest;
import com.marsus.demo.cardfactory.rest.model.UpdateCardRequest;
import com.marsus.demo.cardfactory.service.CardService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static com.marsus.demo.cardfactory.model.mapper.CardRequestMapper.*;

/**
 * REST API controller that handles card operations.
 */
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/card-request")
public class CardController {

    private final CardService cardService;

    public CardController(final CardService cardService) {
        this.cardService = cardService;
    }

    /**
     * Create a request for a new card. Card info is going to be processed and appropriately stored.
     *
     * @param newCardRequest {@linkplain NewCardRequest} info
     * @return {@linkplain ResponseEntity} with HTTP status {@linkplain org.springframework.http.HttpStatus#CREATED},
     *              holding new card request location
     */
    @PostMapping
    public ResponseEntity<Void> newCardRequest(@RequestBody @NotNull @Valid NewCardRequest newCardRequest) {

        log.info("newCardRequest: new card request arrived, oib: {}", newCardRequest.getOib());

        ClientInfo clientInfo = cardService.createCardRequest(newCardRequest);

        log.info("newCardRequest: new card request stored for client, client ID: {}", clientInfo.getClientId());

        URI cardRequestUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clientInfo.getClientId())
                .toUri();
        return ResponseEntity.created(cardRequestUri).build();
    }

    /**
     * Update card request for the given {@code clientId} and {@code requestId}.
     *
     * @param clientId Client ID
     * @param requestId Request ID
     * @param updateCardRequest {@linkplain UpdateCardRequest} data to be updated
     * @return  {@linkplain ResponseEntity} with HTTP status {@linkplain org.springframework.http.HttpStatus#OK} and
     *          {@linkplain ClientInfo} data in response body
     * @throws NotFoundException if response to be updated is not found
     */
    @PutMapping("/{clientId}/request/{requestId}")
    public ResponseEntity<ClientInfo> updateCardRequest(@PathVariable Long clientId, @PathVariable Long requestId,
            @RequestBody @NotNull @Valid UpdateCardRequest updateCardRequest) throws NotFoundException {

        log.info("updateCardRequest: update card request, client ID: {}, request ID: {}", clientId, requestId);

        ClientInfo clientInfo = cardService.updateCardRequest(mapToUpdateCardRequest(clientId, requestId, updateCardRequest));

        log.info("updateCardRequest: card request updated, client ID: {}, request ID: {}", clientId, requestId);

        return ResponseEntity.ok().body(clientInfo);
    }

    /**
     * Get client info for the given client OIB number.
     *
     * @param oib Client OIB number
     * @return  {@linkplain ResponseEntity} with HTTP status {@linkplain org.springframework.http.HttpStatus#OK} and
     *          {@linkplain ClientInfo} data in response body
     * @throws NotFoundException if a resource for the given OIB is not found
     */
    @GetMapping
    public ResponseEntity<ClientInfo> getClientInfo(@RequestParam @NotNull String oib) throws NotFoundException {

        log.info("getClientInfo: Getting client info for OIB: {}", oib);

        if (!Validator.validateOib(oib)) {
            throw new IllegalArgumentException("Invalid OIB number");
        }

        ClientInfo clientInfo = cardService.getClientInfo(oib);

        log.info("getClientInfo: client info retrieved for OIB: {}", oib);

        return ResponseEntity.ok().body(clientInfo);
    }

    /**
     * Delete all client data for the given OIB number.
     *
     * @param oib Client OIB number
     * @return  {@linkplain ResponseEntity} with HTTP status {@linkplain org.springframework.http.HttpStatus#NO_CONTENT}
     *          if client data is successfully deleted
     * @throws NotFoundException If client to be deleted is not found for the given OIB number
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteClient(@RequestParam @NotNull String oib) throws NotFoundException {

        log.info("deleteClient: Deleting client for OIB: {}", oib);

        if (!Validator.validateOib(oib)) {
            throw new IllegalArgumentException("Invalid OIB number");
        }

        cardService.deleteClient(oib);

        log.info("deleteClient: client deleted for OIB: {}", oib);

        return ResponseEntity.noContent().build();
    }
}
