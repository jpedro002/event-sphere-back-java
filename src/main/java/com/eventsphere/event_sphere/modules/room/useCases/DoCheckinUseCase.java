package com.eventsphere.event_sphere.modules.room.useCases;

import com.eventsphere.event_sphere.modules.room.CheckinEntity;
import com.eventsphere.event_sphere.modules.room.repositories.CheckinRepository;
import com.eventsphere.event_sphere.modules.room.repositories.RoomRepository;
import com.eventsphere.event_sphere.modules.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DoCheckinUseCase {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CheckinRepository checkinRepository;

    @Transactional
    public void execute(UUID roomId, UUID userId) {
        var room = this.roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Sala não encontrada"));

        this.userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!room.isAvailable()) {
            throw new RuntimeException("Sala não disponível para check-in.");
        }

        long checkinCount = this.checkinRepository.countByRoomId(roomId);

        if (checkinCount >= room.getCapacity()) {
            room.setAvailable(false);
            this.roomRepository.save(room);
            throw new RuntimeException("Capacidade máxima da sala atingida. Não é possível fazer o check-in.");
        }

        if (checkinRepository.existsByRoomIdAndUserId(roomId, userId)) {
            throw new RuntimeException("Usuário já fez check-in nesta sala.");
        }

        var checkinEntity = new CheckinEntity();
        checkinEntity.setRoomId(roomId);
        checkinEntity.setUserId(userId);

        System.out.println("Check-in: " + checkinEntity);

        this.checkinRepository.save(checkinEntity);
    }
}
