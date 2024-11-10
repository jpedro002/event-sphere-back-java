package com.eventsphere.event_sphere.modules.room.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRoomDTO {
    private String name;
    private String description;
    private Integer capacity;
    private Boolean available;
}
