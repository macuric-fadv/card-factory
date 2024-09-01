package com.marsus.demo.cardfactory.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * A response class
 */
@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
public class Response {

    /** Response message */
    private String message;
}
