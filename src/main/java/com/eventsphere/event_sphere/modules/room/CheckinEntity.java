package com.eventsphere.event_sphere.modules.room;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity(name = "checkins")
public class CheckinEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(name = "room_id", nullable = false)
    private UUID roomId;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @NotNull
    @CreationTimestamp
    private LocalDateTime checkInTime;

    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
        if (this.checkInTime == null) {
            this.checkInTime = LocalDateTime.now();
        }
    }

}
