package com.eventsphere.event_sphere.modules.user.dto;

import java.util.UUID;

import com.eventsphere.event_sphere.modules.user.RoleEnum;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserReqDTO {

    private UUID id;
    private String name;
    private String email;
    private RoleEnum role;
}
