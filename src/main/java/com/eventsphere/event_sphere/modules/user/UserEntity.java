package com.eventsphere.event_sphere.modules.user;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity(name = "users")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "O campo [name] é obrigatório")
    @Length(min = 8, max = 80, message = "O campo [name] deve conter no minimo (8) e no maximo (80) caracteres")
    private String name;

    @NotNull(message = "O campo [email] é obrigatório")
    @Length(min = 7, max = 80, message = "O campo [email] deve conter no minimo (7) e no maximo (80) caracteres")
    @Email(message = "O campo [email] deve conter um e-mail válido")
    private String email;

    @NotNull(message = "O campo [password] é obrigatório")
    @Length(min = 4, max = 60, message = "A senha deve conter entre (8) e (100) caracteres")
    private String password;

    @NotNull(message = "O campo [role] é obrigatório")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == RoleEnum.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ADMIN"));
        } else {
            return List.of(new SimpleGrantedAuthority("USER"));
        }
    }

    @Override
    public String getUsername() {
        return this.name;
    }
}