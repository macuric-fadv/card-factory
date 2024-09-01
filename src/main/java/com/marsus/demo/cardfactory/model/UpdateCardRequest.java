package com.marsus.demo.cardfactory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * A class representing info required to update card card request data.
 */
@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
public class UpdateCardRequest {

    /** Client ID */
    private Long clientId;

    /** {@linkplain CardRequest} data */
    private CardRequest request;
}
