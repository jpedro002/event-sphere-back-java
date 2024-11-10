package com.eventsphere.event_sphere.modules.room.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventsphere.event_sphere.modules.room.RoomEntity;
import com.eventsphere.event_sphere.modules.room.dto.FormatErrorMessageDTO;
import com.eventsphere.event_sphere.modules.room.dto.FormatResponseAllRoomsDTO;
import com.eventsphere.event_sphere.modules.room.dto.RoomWithParticipantsDTO;
import com.eventsphere.event_sphere.modules.room.dto.UpdateRoomDTO;
import com.eventsphere.event_sphere.modules.room.useCases.CreateRoomUseCase;
import com.eventsphere.event_sphere.modules.room.useCases.DeleteRoomByIdUseCase;
import com.eventsphere.event_sphere.modules.room.useCases.DoCheckinUseCase;
import com.eventsphere.event_sphere.modules.room.useCases.GetAllRoomsUseCase;
import com.eventsphere.event_sphere.modules.room.useCases.GetRoomByIdUseCase;
import com.eventsphere.event_sphere.modules.room.useCases.GetRoomWithParticipantsUseCase;
import com.eventsphere.event_sphere.modules.room.useCases.UpdateRoomUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private CreateRoomUseCase createRoomUseCase;

    @Autowired
    private UpdateRoomUseCase updateRoomUseCase;

    @Autowired
    private GetRoomByIdUseCase getRoomByIdUseCase;

    @Autowired
    private GetAllRoomsUseCase getAllRoomsUseCase;

    @Autowired
    private DeleteRoomByIdUseCase deleteRoomByIdUseCase;

    @Autowired
    private DoCheckinUseCase doCheckinUseCase;

    @Autowired
    private GetRoomWithParticipantsUseCase getRoomWithParticipantsUseCase;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody RoomEntity newRoom) {
        try {
            var r = this.createRoomUseCase.execute(newRoom);
            return ResponseEntity.created(null).body(r);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FormatErrorMessageDTO(e.getMessage()));
        }
    }

    @PutMapping("/{roomID}")
    public ResponseEntity<Object> update(
            @PathVariable String roomID,
            @Valid @RequestBody UpdateRoomDTO updateRoom) {
        try {
            UUID roomUUID = UUID.fromString(roomID);

            this.updateRoomUseCase.execute(roomUUID, updateRoom);
            return ResponseEntity.ok(null);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FormatErrorMessageDTO(e.getMessage()));
        }
    }

    @GetMapping("/{roomID}")
    public ResponseEntity<Object> get(@PathVariable String roomID) {
        try {
            UUID roomUUID = UUID.fromString(roomID);
            var r = this.getRoomByIdUseCase.execute(roomUUID);
            return ResponseEntity.ok().body(r);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FormatErrorMessageDTO(e.getMessage()));
        }
    }

    @GetMapping("")
    public ResponseEntity<Object> getAll() {
        try {
            var r = this.getAllRoomsUseCase.execute();
            return ResponseEntity.ok().body(new FormatResponseAllRoomsDTO(r));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FormatErrorMessageDTO(e.getMessage()));
        }
    }

    @DeleteMapping("/{roomID}")
    public ResponseEntity<Object> deleteRoomById(@PathVariable String roomID) {
        try {
            UUID roomUUID = UUID.fromString(roomID);
            this.deleteRoomByIdUseCase.execute(roomUUID);
            return ResponseEntity.ok(null);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FormatErrorMessageDTO(e.getMessage()));
        }
    }

    @PostMapping("/checkin/{roomId}/{userId}")
    public ResponseEntity<Object> checkInUser(@PathVariable String roomId, @PathVariable String userId) {
        try {
            UUID roomUUID = UUID.fromString(roomId);
            UUID userUUID = UUID.fromString(userId);

            this.doCheckinUseCase.execute(roomUUID, userUUID);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FormatErrorMessageDTO(e.getMessage()));
        }
    }

    @GetMapping("/{roomId}/participants")
    public ResponseEntity<Object> getRoomWithParticipants(@PathVariable UUID roomId) {
        try {
            RoomWithParticipantsDTO response = getRoomWithParticipantsUseCase.execute(roomId);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FormatErrorMessageDTO(e.getMessage()));
        }
    }

}
