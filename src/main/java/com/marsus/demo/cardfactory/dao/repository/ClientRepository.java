package com.marsus.demo.cardfactory.dao.repository;

import com.marsus.demo.cardfactory.dao.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * A JPA repository class for a {@linkplain ClientEntity}
 */
@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    Optional<ClientEntity> findByOib(String oib);
}
