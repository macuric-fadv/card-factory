package com.marsus.demo.cardfactory.repository;

import com.marsus.demo.cardfactory.model.entity.CardRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRequestRepository extends JpaRepository<CardRequest, Long> {
}
