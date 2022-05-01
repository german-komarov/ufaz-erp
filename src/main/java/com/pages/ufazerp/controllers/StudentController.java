package com.pages.ufazerp.controllers;

import com.pages.ufazerp.services.StudentService;
import com.pages.ufazerp.util.dto.lesson.GetLessonDto;
import com.pages.ufazerp.util.dto.users.student.CreateStudentDto;
import com.pages.ufazerp.util.dto.users.student.GetStudentDto;
import com.pages.ufazerp.util.exceptions.NotFoundException;
import com.pages.ufazerp.util.exceptions.ValidationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pages.ufazerp.util.tools.JsonUtils.*;

import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.*;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/admin")
    public ResponseEntity<Object> getAllStudents() {
        try {
            return ok(json("students", studentService.readAll().stream().map(GetStudentDto::new).collect(Collectors.toList())));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }


    @GetMapping("/absences")
    public ResponseEntity<Object> getAbsences() {
        try {
            return ok(json("lessons", studentService.readAllAbsences().stream().map(GetLessonDto::new).collect(Collectors.toList())));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudentById(@PathVariable("id") long id) {
        try {
            return ok(json("student", new GetStudentDto(studentService.readById(id))));
        } catch (NotFoundException e) {
            return badRequest().body(message(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> postStudent(@RequestBody CreateStudentDto dto) {
        try {
            return ok(json("student", new GetStudentDto(studentService.createStudent(dto))));
        } catch (ValidationException e) {
            return badRequest().body(message(e));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }
}
