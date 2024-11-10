package com.eventsphere.event_sphere.modules.room.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventsphere.event_sphere.modules.room.RoomEntity;

public interface RoomRepository extends JpaRepository<RoomEntity, UUID> {

}
