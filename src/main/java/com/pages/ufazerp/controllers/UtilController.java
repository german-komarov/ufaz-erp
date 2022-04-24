package com.pages.ufazerp.controllers;

import com.pages.ufazerp.util.constants.Level;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

import static com.pages.ufazerp.util.tools.JsonUtils.json;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/utils")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UtilController {
    @GetMapping("/levels")
    public ResponseEntity<Object> getAllLevels() {
        return ok().body(json("levels", Arrays.asList(Level.values())));
    }
}
