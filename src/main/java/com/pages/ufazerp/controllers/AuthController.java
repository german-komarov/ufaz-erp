package com.pages.ufazerp.controllers;

import com.pages.ufazerp.config.secutiry.jwt.JWTUtil;
import com.pages.ufazerp.domain.Student;
import com.pages.ufazerp.domain.Teacher;
import com.pages.ufazerp.domain.User;
import com.pages.ufazerp.util.dto.LoginCredentials;
import com.pages.ufazerp.util.dto.subject.GetSubjectDto;
import com.pages.ufazerp.util.dto.users.GetUserDto;
import com.pages.ufazerp.util.dto.users.student.GetStudentDto;
import com.pages.ufazerp.util.dto.users.teacher.GetTeacherDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.pages.ufazerp.util.tools.JsonUtils.message;
import static org.springframework.http.ResponseEntity.ok;
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
            Authentication authentication = authManager.authenticate(authInputToken);
            User user = (User) authentication.getPrincipal();
            String token = jwtUtil.generateToken(body.getEmail());
            Map<String, Object> json = new HashMap<>();
            json.put("jwt", token);
            if(user instanceof Student) {
                json.put("user", new GetStudentDto((Student)user));
            } else if(user instanceof Teacher) {
                json.put("user", new GetTeacherDto((Teacher) user));
            } else {
                json.put("user", new GetUserDto(user));
            }
            return ok(json);
        } catch (AuthenticationException e){
            return status(HttpStatus.UNAUTHORIZED).body(message("Wrong credentials"));
        }
    }


}