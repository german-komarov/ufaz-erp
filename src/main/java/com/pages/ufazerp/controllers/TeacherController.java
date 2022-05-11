package com.pages.ufazerp.controllers;

import com.pages.ufazerp.services.TeacherService;
import com.pages.ufazerp.util.dto.users.teacher.CreateTeacherDto;
import com.pages.ufazerp.util.dto.users.teacher.GetTeacherDto;
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
@RequestMapping("/api/teachers")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllTeachers() {
        try {
            return ok(json("teachers", teacherService.readAll().stream().map(GetTeacherDto::new).collect(Collectors.toList())));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTeacher(@PathVariable("id") long id) {
        try {
            return ok(json("teacher", new GetTeacherDto(teacherService.readById(id))));
        } catch (NotFoundException e) {
            return status(NOT_FOUND).body(message(e));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }


    @PostMapping
    public ResponseEntity<Object> postTeacher(@RequestBody CreateTeacherDto dto) {
        try {
            return ok(json("teacher", new GetTeacherDto(teacherService.createTeacher(dto))));
        } catch (ValidationException e) {
            return badRequest().body(message(e));
        } catch (Exception e) {
            return internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTeacher(@PathVariable("id") long id) {
        try {
            teacherService.deleteTeacher(id);
            return ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }
}
