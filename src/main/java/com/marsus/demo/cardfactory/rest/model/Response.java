package com.marsus.demo.cardfactory.rest.model;

import lombok.*;

/**
 * A response class
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response {

    /** Response message */
    private String message;
}
