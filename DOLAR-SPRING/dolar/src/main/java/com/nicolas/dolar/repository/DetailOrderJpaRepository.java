package com.nicolas.dolar.repository;

import com.nicolas.dolar.entities.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface DetailOrderJpaRepository extends JpaRepository<OrderDetailEntity, Long> {
}
