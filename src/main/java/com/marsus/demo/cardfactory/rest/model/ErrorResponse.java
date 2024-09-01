package com.marsus.demo.cardfactory.rest.model;

import lombok.*;

/**
 * A class representing an error response returned from REST API
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    /** HTTP status code value */
    private String code;

    /** ID for auditing purpose */
    private String id;

    /** Error description */
    private String description;
}
