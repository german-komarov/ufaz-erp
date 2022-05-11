package com.pages.ufazerp.controllers;

import com.pages.ufazerp.services.GroupService;
import com.pages.ufazerp.util.dto.group.CreateGroupDto;
import com.pages.ufazerp.util.dto.group.GetGroupDto;
import com.pages.ufazerp.util.dto.group.UpdateGroupDto;
import com.pages.ufazerp.util.exceptions.NotFoundException;
import com.pages.ufazerp.util.exceptions.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static com.pages.ufazerp.util.tools.JsonUtils.json;
import static com.pages.ufazerp.util.tools.JsonUtils.message;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/api/groups")
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
            return ok(json("announce", new GetGroupDto(groupService.updateGroup(id, dto))));
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
