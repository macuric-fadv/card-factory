package com.marsus.demo.cardfactory.rest.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * A class representing request for a new card
 */
@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
public class NewCardRequest {

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
