package com.marsus.demo.cardfactory.service;

import com.marsus.demo.cardfactory.exception.NotFoundException;
import com.marsus.demo.cardfactory.model.ClientInfo;
import com.marsus.demo.cardfactory.model.UpdateCardRequest;
import com.marsus.demo.cardfactory.model.NewCardRequest;

/**
 * Card service class providing various card request operations.
 */
public interface CardService {

    /**
     * Create a request for new card.
     *
     * @param newCardRequest {@linkplain NewCardRequest} holding all necessary data for card creation
     * @return {@linkplain ClientInfo} holding client information
     */
    ClientInfo createCardRequest(NewCardRequest newCardRequest);

    /**
     * Update existing card request.
     *
     * @param updateCardRequest {@linkplain UpdateCardRequest} holding data necessary to update the card request
     * @return {@linkplain ClientInfo} holding client information
     * @throws NotFoundException If card request to be updated is not found
     */
    ClientInfo updateCardRequest(UpdateCardRequest updateCardRequest) throws NotFoundException;

    /**
     * Get all client information for the given OIB number.
     *
     * @param oib Client's OIB number
     * @return  {@linkplain ClientInfo} for the client with the given OIB number
     * @throws NotFoundException If client is not found for the given OIB number
     */
    ClientInfo getClientInfo(String oib) throws NotFoundException;

    /**
     * Delete all client information for the given OIB number.
     *
     * @param oib Client's OIB number
     * @throws NotFoundException If client is not found for the given OIB number
     */
    void deleteClient(String oib) throws NotFoundException;
}
