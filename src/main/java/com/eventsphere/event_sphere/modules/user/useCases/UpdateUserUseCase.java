package com.eventsphere.event_sphere.modules.user.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventsphere.event_sphere.modules.user.UserRepository;
import com.eventsphere.event_sphere.modules.user.dto.UpdateUserReqDTO;

@Service
public class UpdateUserUseCase {

    @Autowired
    private UserRepository userRepository;

    public void execute(UpdateUserReqDTO user) {

        var u = this.userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        if (user.getName() != null) {
            u.setName(user.getName());
        }
        if (user.getEmail() != null) {
            u.setEmail(user.getEmail());

        }
        if (user.getRole() != null) {
            u.setRole(user.getRole());
        }
        this.userRepository.save(u);

    }
}
