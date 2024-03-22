package com.nicolas.dolar.repository;

import com.nicolas.dolar.entities.OrderEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {

    @Query("SELECT o FROM OrderEntity o LEFT JOIN FETCH o.ordersDetails LEFT JOIN FETCH o.editorEntity WHERE o.idOrderp2p = :id")
    Optional<OrderEntity> findOrderWithDetailsById(@Param("id") Long id);


    @EntityGraph(attributePaths = {"ordersDetails"}) // Carga los detalles de las órdenes
    List<OrderEntity> findAll();


}
