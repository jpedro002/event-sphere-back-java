package com.eventsphere.event_sphere.modules.room.useCases;

import com.eventsphere.event_sphere.modules.room.RoomEntity;
import com.eventsphere.event_sphere.modules.room.dto.UpdateRoomDTO;
import com.eventsphere.event_sphere.modules.room.repositories.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateRoomUseCase {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    public UpdateRoomUseCase(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public RoomEntity execute(UUID roomId, UpdateRoomDTO updateData) {
        Optional<RoomEntity> optionalRoom = roomRepository.findById(roomId);
        if (optionalRoom.isEmpty()) {
            throw new RuntimeException("Room not found");
        }

        RoomEntity room = optionalRoom.get();

        if (updateData.getName() != null) {
            room.setName(updateData.getName());
        }

        if (updateData.getDescription() != null) {
            room.setDescription(updateData.getDescription());
        }

        if (updateData.getCapacity() != null) {
            room.setCapacity(updateData.getCapacity());
        }

        if (updateData.getAvailable() != null) {
            room.setAvailable(updateData.getAvailable());
        }

        return roomRepository.save(room);
    }
}
