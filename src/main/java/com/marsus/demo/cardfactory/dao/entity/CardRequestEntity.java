package com.marsus.demo.cardfactory.dao.entity;

import com.marsus.demo.cardfactory.model.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * An entity class representing a new card request data. Request is identified by its ID.
 * {@linkplain Status} denotes a stage the process of creating new credit card is at.
 * Credit card request belongs to a single {@linkplain ClientEntity}.
 */
@Data
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "CARD_REQUEST")
public class CardRequestEntity extends BaseEntity {

    public CardRequestEntity() {
        this.status = Status.NEW;
    }

    private LocalDateTime completedDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;
}
