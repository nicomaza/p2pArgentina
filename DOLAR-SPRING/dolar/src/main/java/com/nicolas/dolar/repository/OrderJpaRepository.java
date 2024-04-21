package com.nicolas.dolar.repository;

import com.nicolas.dolar.dtos.enums.StatusOrder;
import com.nicolas.dolar.dtos.enums.typeReview;
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


    @Query("SELECT o FROM OrderEntity o LEFT JOIN FETCH o.ordersDetails LEFT JOIN FETCH o.editorEntity WHERE o.status = :status")
    List<OrderEntity> findByStatus(@Param("status") StatusOrder status);

    @Query("SELECT o FROM OrderEntity o LEFT JOIN FETCH o.ordersDetails LEFT JOIN FETCH o.editorEntity WHERE (o.status = :status1 OR o.status = :status2) AND o.editorEntity.idUser = :id")
    List<OrderEntity> ordersActiveByUser(@Param("status1") StatusOrder status1, @Param("status2") StatusOrder status2, @Param("id") Long id);




    @EntityGraph(attributePaths = {"ordersDetails"}) // Carga los detalles de las órdenes
    List<OrderEntity> findAll();


    @Query("SELECT COUNT(o) FROM OrderEntity o WHERE o.editorEntity.idUser = :userId AND o.status = :status")
    Long countCompletedOrdersByUserId(@Param("userId") Long userId, @Param("status") StatusOrder status);

    @Query("SELECT COUNT(o) FROM OrderEntity o WHERE o.editorEntity.idUser = :userId AND o.typeForEditor = :type")
    Long countNumberOfPositives(@Param("userId") Long userId, @Param("type") typeReview type);



}
