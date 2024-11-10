package com.eventsphere.event_sphere.modules.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventsphere.event_sphere.modules.user.UserEntity;
import com.eventsphere.event_sphere.modules.user.dto.LoginReqDTO;
import com.eventsphere.event_sphere.modules.user.useCases.CreateUserUseCase;
import com.eventsphere.event_sphere.modules.user.useCases.LoginUserUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private CreateUserUseCase createUserUseCase;

    @Autowired
    private LoginUserUseCase loginUserUseCase;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody UserEntity newUser) {
        try {

            var res = this.createUserUseCase.execute(newUser);
            return ResponseEntity.created(null).body(res);

        } catch (Exception e) {

            if (e.getMessage().equals("usuario já cadastrado")) {
                return ResponseEntity.status(409).body(new ErrorMessageDTO("User already registered"));
            }

            return ResponseEntity.badRequest().body(new ErrorMessageDTO(e.getMessage()));
        }

    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@Valid @RequestBody LoginReqDTO user) {
        try {
            var u = this.loginUserUseCase.execute(user);
            return ResponseEntity.ok().body(u);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessageDTO(e.getMessage()));
        }
    }

    record ErrorMessageDTO(String message) {
    }

}
