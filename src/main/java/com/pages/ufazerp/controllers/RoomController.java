package com.pages.ufazerp.controllers;

import com.pages.ufazerp.domain.Room;
import com.pages.ufazerp.services.RoomService;
import com.pages.ufazerp.util.dto.room.CreateRoomDto;
import com.pages.ufazerp.util.dto.room.GetRoomDto;
import com.pages.ufazerp.util.dto.room.UpdateRoomDto;
import com.pages.ufazerp.util.exceptions.NotFoundException;
import com.pages.ufazerp.util.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.pages.ufazerp.util.tools.JsonUtils.message;
import static org.springframework.http.ResponseEntity.*;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static com.pages.ufazerp.util.tools.JsonUtils.json;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public ResponseEntity<Object> getRooms() {
        try {
            return ok(json("rooms", roomService.readAll().stream().map(GetRoomDto::new).collect(Collectors.toList())));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRoomById(@PathVariable("id") long id) {
        try {
            return ok(json("room", roomService.readById(id)));
        } catch (NotFoundException e) {
            return status(NOT_FOUND).body(message(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> postRoom(@RequestBody CreateRoomDto roomDto) {
        try {
            Room room = roomService.createRoom(roomDto);
            return ok().body(json("room", new GetRoomDto(room)));
        } catch (ValidationException e) {
            return badRequest().body(message(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> putRoom(@PathVariable("id") long id, @RequestBody UpdateRoomDto roomDto) {
        try {
            roomService.updateRoom(id, roomDto);
            return ok().build();
        } catch (NotFoundException e) {
            return status(NOT_FOUND).body(message(e.getMessage()));
        } catch (ValidationException e) {
            return badRequest().body(message(e.getMessage()));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRoom(@PathVariable("id") long id) {
        try {
            roomService.deleteRoom(id);
            return ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }
}
