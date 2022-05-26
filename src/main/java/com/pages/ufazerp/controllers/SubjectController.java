package com.pages.ufazerp.controllers;

import com.pages.ufazerp.domain.Subject;
import com.pages.ufazerp.services.SubjectService;
import com.pages.ufazerp.util.dto.subject.CreateSubjectDto;
import com.pages.ufazerp.util.dto.subject.GetSubjectDto;
import com.pages.ufazerp.util.dto.subject.UpdateSubjectDto;
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
@RequestMapping("/api/subjects")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        try {
            return ok(json("subjects", subjectService.readAll().stream().map(GetSubjectDto::new).collect(Collectors.toList())));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") long id) {
        try {
            return ok(json("subject", new GetSubjectDto(subjectService.readById(id))));
        } catch (NotFoundException e) {
            return badRequest().body(message(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> postSubject(@RequestBody CreateSubjectDto subjectDto) {
        try {
            Subject subject = subjectService.createSubject(subjectDto);
            return ok(json("subject", new GetSubjectDto(subject)));
        } catch (ValidationException e) {
            return badRequest().body(message(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putSubject(@PathVariable("id") long id, @RequestBody UpdateSubjectDto subjectDto) {
        try {
            return ok(json("subject", new GetSubjectDto(subjectService.updateSubject(id, subjectDto))));
        } catch (NotFoundException e) {
            return status(NOT_FOUND).body(message(e.getMessage()));
        } catch (ValidationException e) {
            return badRequest().body(message(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSubject(@PathVariable("id") long id) {
        try {
            subjectService.deleteSubject(id);
            return ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }
}
