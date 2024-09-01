package com.marsus.demo.cardfactory.model;

import lombok.*;

/**
 * A class representing card request data.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardRequest {

    /** Card request ID */
    private Long requestId;

    /** Card request {@linkplain Status} */
    private Status status;
}
