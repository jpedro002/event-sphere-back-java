package com.eventsphere.event_sphere.modules.room.useCases;

import com.eventsphere.event_sphere.modules.room.CheckinEntity;
import com.eventsphere.event_sphere.modules.room.RoomEntity;
import com.eventsphere.event_sphere.modules.room.repositories.CheckinRepository;
import com.eventsphere.event_sphere.modules.room.repositories.RoomRepository;
import com.eventsphere.event_sphere.modules.user.UserEntity;
import com.eventsphere.event_sphere.modules.user.UserRepository;
import com.eventsphere.event_sphere.modules.room.dto.ParticipantDTO;
import com.eventsphere.event_sphere.modules.room.dto.RoomWithParticipantsDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GetRoomWithParticipantsUseCase {

    @Autowired
    private CheckinRepository checkinRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    public RoomWithParticipantsDTO execute(UUID roomId) {
        RoomEntity room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        List<CheckinEntity> checkins = checkinRepository.findByRoomId(roomId);

        List<ParticipantDTO> participants = checkins.stream().map(checkin -> {
            UserEntity user = userRepository.findById(checkin.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            return new ParticipantDTO(user.getId(), user.getName(), user.getEmail());
        }).collect(Collectors.toList());

        return new RoomWithParticipantsDTO(room, participants);
    }
}
