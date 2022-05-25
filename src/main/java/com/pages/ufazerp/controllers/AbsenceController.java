package com.pages.ufazerp.controllers;

import com.pages.ufazerp.services.AbsenceService;
import com.pages.ufazerp.util.dto.absence.CreateAbsencesDto;
import com.pages.ufazerp.util.dto.absence.GetAbsenceDto;
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
@RequestMapping("/api/absences")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AbsenceController {

    private final AbsenceService absenceService;

    public AbsenceController(AbsenceService absenceService) {
        this.absenceService = absenceService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllAbsences() {
        try {
            return ok(json("absences", absenceService.readAll().stream().map(GetAbsenceDto::new).collect(Collectors.toList())));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

    @GetMapping("/by/student/{id}")
    public ResponseEntity<Object> getAllAbsencesByStudent(@PathVariable("id") long id) {
        try {
            return ok(json("absences", absenceService.readAllByStudentId(id).stream().map(GetAbsenceDto::new).collect(Collectors.toList())));
        } catch (NotFoundException e) {
            return status(NOT_FOUND).body(message(e));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

    @GetMapping("/lesson/{id}")
    public ResponseEntity<Object> getAllAbsencesByLesson(@PathVariable("id") long id) {
        try {
            return ok(json("absences", absenceService.readAllByLessonId(id).stream().map(GetAbsenceDto::new).collect(Collectors.toList())));
        } catch (NotFoundException e) {
            return status(NOT_FOUND).body(message(e));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> postAbsence(@RequestBody CreateAbsencesDto dto) {
        try {
            absenceService.createAbsences(dto);
            return ok().build();
        } catch (ValidationException e) {
            return badRequest().body(message(e));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAbsence(@PathVariable("id") long id) {
        try {
            absenceService.deleteAbsence(id);
            return ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }
}
