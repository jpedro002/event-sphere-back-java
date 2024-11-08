package com.eventsphere.event_sphere.modules.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginReqDTO {

    @NotBlank(message = "O campo [email] é obrigatório.")
    @Email(message = "O campo [email] deve conter um e-mail válido.")
    private String email;

    @NotBlank(message = "O campo [password] é obrigatório.")
    @Size(min = 6, max = 60, message = "A senha deve ter entre 6 e 60 caracteres.")
    private String password;
}
