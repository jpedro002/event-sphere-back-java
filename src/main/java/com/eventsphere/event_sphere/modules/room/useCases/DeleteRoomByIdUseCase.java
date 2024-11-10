package com.eventsphere.event_sphere.modules.room.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventsphere.event_sphere.modules.room.repositories.RoomRepository;

@Service
public class DeleteRoomByIdUseCase {

    @Autowired
    private RoomRepository roomRepository;

    public void execute(UUID roomId) {
        roomRepository.deleteById(roomId);
    }
}
