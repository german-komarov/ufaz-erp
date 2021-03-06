package com.pages.ufazerp.controllers;

import com.pages.ufazerp.services.LessonService;
import com.pages.ufazerp.util.dto.lesson.CreateOrUpdateLessonDto;
import com.pages.ufazerp.util.dto.lesson.GetLessonDto;
import com.pages.ufazerp.util.dto.users.student.GetStudentDto;
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
@RequestMapping("/api/lessons")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllLessons() {
        try {
            return ok(json("lessons", lessonService.readAllLessons().stream().map(GetLessonDto::new).collect(Collectors.toList())));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getLessonById(@PathVariable("id") long id) {
        try {
            return ok(json("lesson", new GetLessonDto(lessonService.readById(id))));
        } catch (NotFoundException e) {
            return status(NOT_FOUND).body(message(e));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<Object> getAllStudentOfLesson(@PathVariable("id") long id) {
        try {
            return ok(json("students", lessonService.readAllStudentsOfLesson(id).stream().map(GetStudentDto::new).collect(Collectors.toList())));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> postLesson(@RequestBody CreateOrUpdateLessonDto dto) {
        try {
            return ok(json("lesson", new GetLessonDto(lessonService.createLesson(dto))));
        } catch (ValidationException e) {
            return badRequest().body(message(e));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putLesson(@PathVariable("id") long id, @RequestBody CreateOrUpdateLessonDto dto) {
        try {
            return ok(json("lesson", new GetLessonDto(lessonService.update(id, dto))));
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
    public ResponseEntity<Object> deleteLesson(@PathVariable("id") long id) {
        try {
            lessonService.delete(id);
            return ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }
}
