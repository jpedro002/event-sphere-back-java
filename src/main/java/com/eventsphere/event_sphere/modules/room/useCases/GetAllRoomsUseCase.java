package com.eventsphere.event_sphere.modules.room.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventsphere.event_sphere.modules.room.RoomEntity;
import com.eventsphere.event_sphere.modules.room.repositories.RoomRepository;

@Service
public class GetAllRoomsUseCase {

    @Autowired
    private RoomRepository roomRepository;

    public List<RoomEntity> execute() {
        return roomRepository.findAll();
    }
}
