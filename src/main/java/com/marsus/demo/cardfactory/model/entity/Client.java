package com.marsus.demo.cardfactory.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * A client entity, applying for a new credit card. Client is identified by its ID, has personal data
 * like first name, last name, and OIB. A client can have one or more {@linkplain CardRequest}s.
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Getter
@Entity
@Table(name = "CLIENT", indexes = @Index(name = "idx_oib", columnList = "oib"))
public class Client extends BaseEntity {

    private String firstName;

    private String lastName;

    private String oib;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<CardRequest> cardRequests;

    public List<CardRequest> getCardRequests() {
        if (cardRequests == null) {
            cardRequests = new ArrayList<>();
        }
        return cardRequests;
    }

    public void addCardRequest(final CardRequest cardRequest) {
        getCardRequests().add(cardRequest);
    }
}
