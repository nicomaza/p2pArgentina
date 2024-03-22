package com.nicolas.dolar.repository;

import com.nicolas.dolar.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userJpaRepository extends JpaRepository<UserEntity, Long> {
}
