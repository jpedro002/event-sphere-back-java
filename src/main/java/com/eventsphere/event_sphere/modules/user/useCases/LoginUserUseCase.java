package com.eventsphere.event_sphere.modules.user.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eventsphere.event_sphere.modules.user.UserEntity;
import com.eventsphere.event_sphere.modules.user.UserRepository;
import com.eventsphere.event_sphere.modules.user.dto.LoginReqDTO;
import com.eventsphere.event_sphere.modules.user.dto.LoginResDTO;
import com.eventsphere.event_sphere.modules.user.dto.UserFormatedDTO;
import com.eventsphere.event_sphere.security.TokenService;

@Service
public class LoginUserUseCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    public LoginResDTO execute(LoginReqDTO user) {
        UserEntity u = this.userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        if (!passwordEncoder.matches(user.getPassword(), u.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        String token = tokenService.generateToken(u);

        return new LoginResDTO(token, new UserFormatedDTO(
                u.getEmail(),
                u.getName(),
                u.getRole().getRole(),
                u.getId()));
    }
}
