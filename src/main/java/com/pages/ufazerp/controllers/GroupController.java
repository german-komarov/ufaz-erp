package com.pages.ufazerp.controllers;

import com.pages.ufazerp.services.GroupService;
import com.pages.ufazerp.util.dto.group.CreateGroupDto;
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

@RestController
@RequestMapping("/api/admin/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseEntity<Object> postGroup(@RequestBody CreateGroupDto dto) {
        try {
            return ok(json("group", groupService.createGroup(dto)));
        } catch (ValidationException e) {
            return badRequest().body(message(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

}
