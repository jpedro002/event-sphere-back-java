package com.eventsphere.event_sphere.modules.room.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventsphere.event_sphere.modules.room.CheckinEntity;

public interface CheckinRepository extends JpaRepository<CheckinEntity, UUID> {

    long countByRoomId(UUID roomId);

    boolean existsByRoomIdAndUserId(UUID roomId, UUID userId);

    List<CheckinEntity> findByRoomId(UUID roomId);
}
