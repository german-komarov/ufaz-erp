package com.pages.ufazerp.controllers;

import com.pages.ufazerp.services.LessonService;
import com.pages.ufazerp.util.dto.lesson.CreateLessonDto;
import com.pages.ufazerp.util.dto.lesson.GetLessonDto;
import com.pages.ufazerp.util.exceptions.ValidationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.http.ResponseEntity;

import static com.pages.ufazerp.util.tools.JsonUtils.json;
import static com.pages.ufazerp.util.tools.JsonUtils.message;
import static org.springframework.http.ResponseEntity.*;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping
    public ResponseEntity<Object> postLesson(@RequestBody CreateLessonDto dto) {
        try {
            return ok(json("lesson", new GetLessonDto(lessonService.createLesson(dto))));
        } catch (ValidationException e) {
            return badRequest().body(message(e));
        } catch (Exception e) {
            return internalServerError().build();
        }
    }

}
