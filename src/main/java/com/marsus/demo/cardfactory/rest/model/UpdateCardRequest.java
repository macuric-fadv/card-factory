package com.marsus.demo.cardfactory.rest.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * A class representing card info necessary for updating card request.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCardRequest {

    /** New card status */
    @NotNull
    private String status;
}
