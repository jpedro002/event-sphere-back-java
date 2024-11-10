package com.eventsphere.event_sphere.modules.room.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ParticipantDTO {
    private UUID id;
    private String nome;
    private String email;
}
