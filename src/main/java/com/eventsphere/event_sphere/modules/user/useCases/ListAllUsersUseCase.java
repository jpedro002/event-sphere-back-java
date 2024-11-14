package com.eventsphere.event_sphere.modules.user.useCases;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventsphere.event_sphere.modules.user.UserRepository;
import com.eventsphere.event_sphere.modules.user.dto.PickUsersInfoOnListAllDTO;

@Service
public class ListAllUsersUseCase {

    @Autowired
    private UserRepository userRepository;

    public List<PickUsersInfoOnListAllDTO> execute() {
        var users = this.userRepository.findAll();

        return users.stream().map(u -> {
            return new PickUsersInfoOnListAllDTO(u.getId(),
                    u.getName(),
                    u.getEmail(),
                    u.getRole().name(),
                    u.isEnabled(),
                    u.getCreatedAt());
        }).collect(Collectors.toList());
    }
}
