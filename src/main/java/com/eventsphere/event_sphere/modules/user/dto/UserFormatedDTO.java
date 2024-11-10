package com.eventsphere.event_sphere.modules.user.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserFormatedDTO {
    private String email;
    private String name;
    private String role;
    private UUID id;

}
