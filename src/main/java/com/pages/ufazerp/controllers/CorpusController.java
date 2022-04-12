package com.pages.ufazerp.controllers;

import com.pages.ufazerp.domain.Corpus;
import com.pages.ufazerp.services.CorpusService;
import com.pages.ufazerp.util.dto.corpus.CreateCorpusDto;
import com.pages.ufazerp.util.dto.corpus.GetCorpusDto;
import com.pages.ufazerp.util.dto.corpus.UpdateCorpusDto;
import com.pages.ufazerp.util.exceptions.NotFoundException;
import com.pages.ufazerp.util.exceptions.ValidationException;
import static com.pages.ufazerp.util.tools.JsonUtils.*;

import static org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/corpora")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CorpusController {

    private final CorpusService service;

    public CorpusController(CorpusService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<Object> getCorpora() {
        try {
            return ok(json("corpora", service.readAll()));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getCorpusById(@PathVariable("id") long id) {
        try {
            return ok(json("corpus", new GetCorpusDto(service.readById(id))));
        } catch (NotFoundException e) {
            return status(NOT_FOUND).body(json("message", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> postCorpus(@RequestBody CreateCorpusDto corpusDto) {
        try {
            Corpus corpus = service.createCorpus(corpusDto);
            return ok(json("corpus", new GetCorpusDto(corpus)));
        } catch (ValidationException e) {
            return badRequest().body(e.getMessage());
        } catch (Exception e) {
            return internalServerError().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> putCorpus(@PathVariable("id") long id,
                                            @RequestBody UpdateCorpusDto corpusDto) {
        try {
            service.updateCorpus(id, corpusDto);
            return ok().build();
        } catch (NotFoundException e) {
            return status(NOT_FOUND).body(json("message", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteCorpus(@PathVariable("id") long id) {
        try {
            service.deleteCorpus(id);
            return ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }
}
