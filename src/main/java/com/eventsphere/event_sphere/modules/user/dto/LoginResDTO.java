package com.eventsphere.event_sphere.modules.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResDTO {

    private String token;
    private UserFormatedDTO user;
}
