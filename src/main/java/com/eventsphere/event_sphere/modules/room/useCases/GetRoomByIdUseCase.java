package com.eventsphere.event_sphere.modules.room.useCases;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventsphere.event_sphere.modules.room.RoomEntity;
import com.eventsphere.event_sphere.modules.room.repositories.RoomRepository;

@Service
public class GetRoomByIdUseCase {

    @Autowired
    private RoomRepository roomRepository;

    public RoomEntity execute(UUID roomId) {
        Optional<RoomEntity> optionalRoom = roomRepository.findById(roomId);
        if (optionalRoom.isEmpty()) {
            throw new RuntimeException("Room not found");
        }

        return optionalRoom.get();
    }

}
