package com.marsus.demo.cardfactory.model;

import lombok.*;

import java.util.List;

/**
 * A class representing all client information.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientInfo {

    /** Client ID */
    private Long clientId;

    /** Client first name */
    private String firstName;

    /** Client last name */
    private String lastName;

    /** Client OIB number */
    private String oib;

    /** List of client's {@linkplain CardRequest}s for a new card */
    private List<CardRequest> requests;
}
