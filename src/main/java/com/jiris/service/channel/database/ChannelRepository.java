package com.jiris.service.channel.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChannelRepository extends JpaRepository<ChannelEntity, UUID> {
    boolean existsByName(String name);
    ChannelEntity findByName(String name);
}
