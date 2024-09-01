package com.marsus.demo.cardfactory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * A class representing card request data.
 */
@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
public class CardRequest {

    /** Card request ID */
    private Long requestId;

    /** Card request {@linkplain Status} */
    private Status status;
}
