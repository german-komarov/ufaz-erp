package com.pages.ufazerp.controllers;

import com.pages.ufazerp.services.LessonService;
import com.pages.ufazerp.util.dto.lesson.CreateLessonDto;
import com.pages.ufazerp.util.dto.lesson.GetLessonDto;
import com.pages.ufazerp.util.dto.lesson.PutAbsencesDto;
import com.pages.ufazerp.util.dto.users.student.GetStudentDto;
import com.pages.ufazerp.util.exceptions.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.pages.ufazerp.util.tools.JsonUtils.json;
import static com.pages.ufazerp.util.tools.JsonUtils.message;
import static org.springframework.http.ResponseEntity.*;


@RestController
@RequestMapping("/api/lessons")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
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
    public ResponseEntity<Object> postLesson(@RequestBody CreateLessonDto dto) {
        try {
            return ok(json("lesson", new GetLessonDto(lessonService.createLesson(dto))));
        } catch (ValidationException e) {
            return badRequest().body(message(e));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

    @PutMapping("{id}/absence")
    public ResponseEntity<Object> putAbsence(@PathVariable("id") long id, @RequestBody PutAbsencesDto dto) {
        try {
            lessonService.putAbsence(id, dto.getStudents());
            return ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

}
