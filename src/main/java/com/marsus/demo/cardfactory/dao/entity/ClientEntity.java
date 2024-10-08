package com.marsus.demo.cardfactory.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * A client entity, applying for a new credit card. Client is identified by its ID, has personal data
 * like first name, last name, and OIB. A client can have one or more {@linkplain CardRequestEntity}s.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "CLIENT", indexes = @Index(name = "idx_oib", columnList = "oib"))
public class ClientEntity extends BaseEntity {

    private String firstName;

    private String lastName;

    private String oib;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CardRequestEntity> cardRequests;

    public List<CardRequestEntity> getCardRequests() {
        if (cardRequests == null) {
            cardRequests = new ArrayList<>();
        }
        return cardRequests;
    }

    public void addCardRequest(final CardRequestEntity cardRequest) {
        getCardRequests().add(cardRequest);
    }
}
