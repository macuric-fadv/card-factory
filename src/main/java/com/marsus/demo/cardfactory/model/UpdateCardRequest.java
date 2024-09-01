package com.marsus.demo.cardfactory.model;

import lombok.*;

/**
 * A class representing info required to update card card request data.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCardRequest {

    /** Client ID */
    private Long clientId;

    /** {@linkplain CardRequest} data */
    private CardRequest request;
}
