package com.pages.ufazerp.controllers;

import com.pages.ufazerp.services.GroupService;
import com.pages.ufazerp.util.dto.group.CreateGroupDto;
import com.pages.ufazerp.util.dto.group.GetGroupDto;
import com.pages.ufazerp.util.dto.group.UpdateGroupDto;
import com.pages.ufazerp.util.exceptions.NotFoundException;
import com.pages.ufazerp.util.exceptions.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.pages.ufazerp.util.tools.JsonUtils.*;

import static org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.*;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }


    @GetMapping
    public ResponseEntity<Object> getAllGroups() {
        try {
            return ok(json("groups", groupService.readAll().stream().map(GetGroupDto::new).collect(Collectors.toList())));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getGroupById(@PathVariable("id") long id) {
        try {
            return ok(json("group", new GetGroupDto(groupService.readById(id))));
        } catch (NotFoundException e) {
            return status(NOT_FOUND).body(message(e));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> postGroup(@RequestBody CreateGroupDto dto) {
        try {
            return ok(json("group", new GetGroupDto(groupService.createGroup(dto))));
        } catch (ValidationException e) {
            return badRequest().body(message(e));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> putGroup(@PathVariable("id") long id, @RequestBody UpdateGroupDto dto) {
        try {
            groupService.updateGroup(id, dto);
            return ok().build();
        } catch (NotFoundException e) {
            return status(NOT_FOUND).body(message(e));
        } catch (ValidationException e) {
            return badRequest().body(message(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGroup(@PathVariable("id") long id) {
        try {
            groupService.deleteGroup(id);
            return ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

}
