package com.marsus.demo.cardfactory.repository;

import com.marsus.demo.cardfactory.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByOib(String oib);
}
