package com.eventsphere.event_sphere.modules.user.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PickUsersInfoOnListAllDTO {

    private UUID id;
    private String name;
    private String email;
    private String role;
    private boolean enabled;
    private LocalDateTime createdAt;

}