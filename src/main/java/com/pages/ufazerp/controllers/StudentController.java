package com.pages.ufazerp.controllers;

import com.pages.ufazerp.services.StudentService;
import com.pages.ufazerp.util.dto.lesson.GetLessonDto;
import com.pages.ufazerp.util.dto.users.student.CreateStudentDto;
import com.pages.ufazerp.util.dto.users.student.GetStudentDto;
import com.pages.ufazerp.util.dto.users.student.UpdateStudentDto;
import com.pages.ufazerp.util.exceptions.NotFoundException;
import com.pages.ufazerp.util.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static com.pages.ufazerp.util.tools.JsonUtils.json;
import static com.pages.ufazerp.util.tools.JsonUtils.message;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllStudents() {
        try {
            return ok(json("students", studentService.readAll().stream().map(GetStudentDto::new).collect(Collectors.toList())));
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
            return badRequest().body(message(e));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

    @GetMapping("/{id}/lessons")
    public ResponseEntity<Object> getAllLessonsForStudent(@PathVariable("id") long id) {
        try {
            return ok(json("lessons", studentService.readAllLessonsByStudentId(id).stream().map(GetLessonDto::new).collect(Collectors.toList())));
        } catch (NotFoundException e) {
            return status(NOT_FOUND).body(message(e));
        } catch (Exception e) {
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


    @PutMapping("/{id}")
    public ResponseEntity<Object> putStudent(@PathVariable("id") long id, @RequestBody UpdateStudentDto dto) {
        try {
            return ok(json("student", studentService.updateStudent(id, dto)));
        } catch (NotFoundException e) {
            return status(NOT_FOUND).body(message(e));
        } catch (ValidationException e) {
            return badRequest().body(message(e));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudentById(@PathVariable("id") long id) {
        try {
            studentService.deleteStudent(id);
            return ok().build();
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
}
