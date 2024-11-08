package com.eventsphere.event_sphere.modules.user.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eventsphere.event_sphere.modules.user.UserEntity;
import com.eventsphere.event_sphere.modules.user.UserRepository;

@Service
public class CreateUserUseCase {
    @Autowired
    private UserRepository userRepository;

    public UserEntity execute(UserEntity newUser) {
        this.userRepository.findByEmail(newUser.getEmail()).ifPresent(user -> {
            throw new RuntimeException("usuario já cadastrado");
        });

        String hashPassword = new BCryptPasswordEncoder().encode(newUser.getPassword());

        newUser.setPassword(hashPassword);

        return this.userRepository.save(newUser);
    }
}
