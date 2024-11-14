package com.eventsphere.event_sphere.modules.user.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventsphere.event_sphere.modules.user.UserEntity;
import com.eventsphere.event_sphere.modules.user.UserRepository;

@Service
public class GetUserByIDUseCase {

    @Autowired
    private UserRepository userRepository;

    public UserEntity execute(UUID userID) {
        var u = this.userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        return u;
    }
}
