package com.pages.ufazerp.controllers;

import com.pages.ufazerp.util.dto.LoginCredentials;
import com.pages.ufazerp.config.secutiry.jwt.JWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

import static com.pages.ufazerp.util.tools.JsonUtils.message;
import static org.springframework.http.ResponseEntity.ok;
import static com.pages.ufazerp.util.tools.JsonUtils.json;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    private final JWTUtil jwtUtil;
    private final AuthenticationManager authManager;

    public AuthController(JWTUtil jwtUtil, AuthenticationManager authManager) {
        this.jwtUtil = jwtUtil;
        this.authManager = authManager;
    }


    @PostMapping("/login")
    public ResponseEntity<Object> loginHandler(@RequestBody LoginCredentials body){
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());
            authManager.authenticate(authInputToken);
            String token = jwtUtil.generateToken(body.getEmail());

            return ok(json("jwt", token));
        } catch (AuthenticationException e){
            return status(HttpStatus.UNAUTHORIZED).body(message("Wrong credentials"));
        }
    }


}