package com.jiris.service.user.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    @Query("SELECT user FROM UserEntity user WHERE user.username = :username")
    UserEntity findByUsername(@Param("username") String username);

    boolean existsByUsername(@Param("username") String username);
}
