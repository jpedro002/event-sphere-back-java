package com.eventsphere.event_sphere.modules.user.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventsphere.event_sphere.modules.user.UserRepository;

@Service
public class DeleteUserUseCase {

    @Autowired
    private UserRepository userRepository;

    public void execute(UUID userID) {
        var u = this.userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        this.userRepository.delete(u);
    }
}
