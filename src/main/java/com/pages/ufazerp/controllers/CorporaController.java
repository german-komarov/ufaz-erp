package com.pages.ufazerp.controllers;

import com.pages.ufazerp.domain.Corpus;
import com.pages.ufazerp.services.CorpusService;
import com.pages.ufazerp.util.dto.CreateCorpusDto;
import com.pages.ufazerp.util.dto.GetCorpusDto;
import com.pages.ufazerp.util.exceptions.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/corpora")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CorporaController {

    private final CorpusService service;

    public CorporaController(CorpusService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<Object> getCorpora() {
        try {
            return ResponseEntity.ok(service.readAll());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> postCorpus(@RequestBody CreateCorpusDto corpusDto) {
        try {
            Corpus corpus = service.createCorpus(corpusDto);
            return ResponseEntity.ok(new GetCorpusDto(
                    corpus.getId(),
                    corpus.getName(),
                    corpus.getAddress(),
                    corpus.getCreatedAt(),
                    corpus.getUpdatedAt()
            ));
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
