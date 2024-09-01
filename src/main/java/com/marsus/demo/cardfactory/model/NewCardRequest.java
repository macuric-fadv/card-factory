package com.marsus.demo.cardfactory.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

/**
 * A class representing request for a new card.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
public class NewCardRequest {

    /** Client ID */
    private Long clientId;

    /** Client first name */
    @NotNull
    @Pattern(regexp = "^[a-zA-Z]{3,50}$")
    private String firstName;

    /** Client last name */
    @NotNull
    @Pattern(regexp = "^[a-zA-Z]{3,50}$")
    private String lastName;

    /** Client OIB number */
    @NotNull
    @Pattern(regexp = "^[0-9]{11}$")
    private String oib;

    /** Card status */
    private String status;
}
