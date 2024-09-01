package com.marsus.demo.cardfactory.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * A class representing an error response returned from REST API
 */
@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
public class ErrorResponse {

    /** HTTP status code value */
    private String code;

    /** ID for auditing purpose */
    private String id;

    /** Error description */
    private String description;
}
