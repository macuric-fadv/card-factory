package com.marsus.demo.ccfactory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

/**
 * A client entity, applying for a new credit card. Client is identified by its ID, has personal data
 * like first name, last name, and OIB. A client can have one or more {@linkplain CreditCardRequest}s.
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@Getter
@Entity
@Table(name = "CLIENT", indexes = @Index(name = "idx_oib", columnList = "oib"))
public class Client extends BaseEntity {

    @Pattern(regexp = "^[a-zA-Z]{3,50}$")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]{3,50}$")
    private String lastName;

    @Pattern(regexp = "^[0-9]{11}$")
    private String oib;

    @OneToMany(mappedBy = "client")
    private List<CreditCardRequest> creditCardRequests;
}
