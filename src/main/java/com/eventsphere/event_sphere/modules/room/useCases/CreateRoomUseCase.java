package com.eventsphere.event_sphere.modules.room.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventsphere.event_sphere.modules.room.RoomEntity;
import com.eventsphere.event_sphere.modules.room.repositories.RoomRepository;

@Service
public class CreateRoomUseCase {

    @Autowired
    private RoomRepository roomRepository;

    public RoomEntity execute(RoomEntity newRoom) {
        return roomRepository.save(newRoom);
    }
}
