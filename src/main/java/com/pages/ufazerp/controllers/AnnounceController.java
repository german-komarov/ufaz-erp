package com.pages.ufazerp.controllers;

import com.pages.ufazerp.services.AnnounceService;
import com.pages.ufazerp.util.dto.announce.CreateAnnounceDto;
import com.pages.ufazerp.util.dto.announce.GetAnnounceDto;
import com.pages.ufazerp.util.dto.announce.UpdateAnnounceDto;
import com.pages.ufazerp.util.exceptions.NotFoundException;
import com.pages.ufazerp.util.exceptions.ValidationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pages.ufazerp.util.tools.JsonUtils.*;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;

import static org.springframework.http.ResponseEntity.*;

import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/announces")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AnnounceController {

    private final AnnounceService announceService;

    public AnnounceController(AnnounceService announceService) {
        this.announceService = announceService;
    }


    @GetMapping
    public ResponseEntity<Object> getAllAnnounces() {
        try {
            return ok(json("announces", announceService.readAllAnnounces().stream().map(GetAnnounceDto::new)
                    .collect(Collectors.toList())));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAnnounce(@PathVariable("id") long id) {
        try {
            return ok(json("announce", new GetAnnounceDto(announceService.readById(id))));
        } catch (NotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(message(e));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> postAnnounce(@RequestBody CreateAnnounceDto dto) {
        try {
            return ok(json("announce", new GetAnnounceDto(announceService.createAnnounce(dto))));
        } catch (ValidationException e) {
            return badRequest().body(message(e));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putAnnounce(@PathVariable("id") long id, @RequestBody UpdateAnnounceDto dto) {
        try {
            return ok(json("announce", new GetAnnounceDto(announceService.updateAnnounce(id, dto))));
        } catch (NotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(message(e));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAnnounce(@PathVariable("id") long id) {
        try {
            announceService.deleteAnnounce(id);
            return ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }
}
