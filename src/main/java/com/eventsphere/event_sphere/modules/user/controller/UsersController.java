package com.eventsphere.event_sphere.modules.user.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventsphere.event_sphere.modules.user.dto.UpdateUserReqDTO;
import com.eventsphere.event_sphere.modules.user.useCases.DeleteUserUseCase;
import com.eventsphere.event_sphere.modules.user.useCases.GetUserByIDUseCase;
import com.eventsphere.event_sphere.modules.user.useCases.ListAllUsersUseCase;
import com.eventsphere.event_sphere.modules.user.useCases.UpdateUserUseCase;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private ListAllUsersUseCase listAllUsersUseCase;

    @Autowired
    private UpdateUserUseCase updateUserUseCase;

    @Autowired
    private DeleteUserUseCase deleteUserUseCase;

    @Autowired
    private GetUserByIDUseCase getUserByIDUseCase;

    @GetMapping("/listAll")
    public ResponseEntity<Object> listAll() {
        try {
            return ResponseEntity.ok().body(this.listAllUsersUseCase.execute());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessageDTO(e.getMessage()));
        }
    }

    @PutMapping("/{userID}")
    public ResponseEntity<Object> update(@PathVariable UUID userID, @RequestBody UpdateUserReqDTO user) {
        try {
            this.updateUserUseCase
                    .execute(new UpdateUserReqDTO(userID, user.getName(), user.getEmail(), user.getRole()));
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessageDTO(e.getMessage()));
        }
    }

    @DeleteMapping("/{userID}")
    public ResponseEntity<Object> delete(@PathVariable UUID userID) {
        try {
            this.deleteUserUseCase.execute(userID);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessageDTO(e.getMessage()));
        }
    }

    @GetMapping("/{userID}")
    public ResponseEntity<Object> getUserByID(@PathVariable UUID userID) {
        try {
            var u = this.getUserByIDUseCase.execute(userID);
            return ResponseEntity.ok().body(u);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorMessageDTO(e.getMessage()));
        }
    }

    record ErrorMessageDTO(String message) {
    }
}
