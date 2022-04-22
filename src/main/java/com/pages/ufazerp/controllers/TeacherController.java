package com.pages.ufazerp.controllers;

import com.pages.ufazerp.services.TeacherService;
import com.pages.ufazerp.util.dto.users.teacher.CreateTeacherDto;
import com.pages.ufazerp.util.dto.users.teacher.GetTeacherDto;
import com.pages.ufazerp.util.exceptions.NotFoundException;
import com.pages.ufazerp.util.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pages.ufazerp.util.tools.JsonUtils.*;

import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.*;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/admin/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllTeachers() {
        try {
            return ok(json("teaches", teacherService.readAll().stream().map(GetTeacherDto::new).collect(Collectors.toList())));
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