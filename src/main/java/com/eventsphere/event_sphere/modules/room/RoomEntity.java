package com.eventsphere.event_sphere.modules.room;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Table(name = "rooms")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "O campo [name] é obrigatório")
    @Size(min = 3, max = 100, message = "O campo [name] deve conter no mínimo (3) e no máximo (100) caracteres")
    private String name;

    @NotNull(message = "O campo [description] é obrigatório")
    @Size(min = 10, max = 255, message = "O campo [description] deve conter no mínimo (10) e no máximo (255) caracteres")
    private String description;

    @NotNull(message = "O campo [capacity] é obrigatório")
    private int capacity;

    private boolean available;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.available = true;
    }

}
