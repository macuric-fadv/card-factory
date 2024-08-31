package com.marsus.demo.cardfactory.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Defines a request for a new credit card. Request is identified by its ID.
 * {@linkplain Status} denotes a stage the process of creating new credit card is at.
 * Credit card request belongs to a single {@linkplain Client}.
 */
@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode(callSuper = true)
@Entity
public class CardRequest extends BaseEntity {

    public CardRequest() {
        this.status = Status.NEW;
    }

    private LocalDateTime completedDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
}
