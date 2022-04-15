package com.pages.ufazerp.controllers;

import com.pages.ufazerp.util.constants.Level;
import com.pages.ufazerp.util.constants.Term;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import static com.pages.ufazerp.util.tools.JsonUtils.json;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/utils")
public class UtilController {
    @GetMapping("/levels")
    public ResponseEntity<Object> getAllLevels() {
        return ok().body(json("levels", Arrays.asList(Level.values())));
    }

    @GetMapping("/terms")
    public ResponseEntity<Object> getAllTerms() {
        return ok().body(json("terms", Arrays.asList(Term.values())));
    }
}