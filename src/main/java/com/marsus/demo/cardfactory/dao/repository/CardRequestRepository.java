package com.marsus.demo.cardfactory.dao.repository;

import com.marsus.demo.cardfactory.dao.entity.CardRequestEntity;
import com.marsus.demo.cardfactory.dao.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A JPA repository class for a {@linkplain CardRequestEntity}
 */
@Repository
public interface CardRequestRepository extends JpaRepository<CardRequestEntity, Long> {
}
