package com.eventsphere.event_sphere.modules.user.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.eventsphere.event_sphere.modules.user.UserEntity;
import com.eventsphere.event_sphere.modules.user.UserRepository;
import com.eventsphere.event_sphere.modules.user.dto.LoginReqDTO;

@Service
public class LoginUserUseCase {

    @Autowired
    private UserRepository userRepository;

    public UsernamePasswordAuthenticationToken execute(LoginReqDTO user) {
        UserEntity u = this.userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        var uas = new UsernamePasswordAuthenticationToken(u, null, u.getAuthorities());

        return uas;
    }

}
