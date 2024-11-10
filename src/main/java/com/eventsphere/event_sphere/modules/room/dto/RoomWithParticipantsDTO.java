package com.eventsphere.event_sphere.modules.room.dto;

import java.util.List;

import com.eventsphere.event_sphere.modules.room.RoomEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomWithParticipantsDTO {
    private RoomEntity room;
    private List<ParticipantDTO> participants;
}
