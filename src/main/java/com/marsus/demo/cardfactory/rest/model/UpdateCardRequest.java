package com.marsus.demo.cardfactory.rest.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * A class representing card info necessary for updating card request.
 */
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class UpdateCardRequest {

    /** New card status */
    @NotNull
    private String status;
}
