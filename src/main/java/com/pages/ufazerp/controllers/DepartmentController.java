package com.pages.ufazerp.controllers;

import com.pages.ufazerp.services.DepartmentService;
import com.pages.ufazerp.util.dto.department.CreateDepartmentDto;
import com.pages.ufazerp.util.dto.department.GetDepartmentDto;
import com.pages.ufazerp.util.dto.department.UpdateDepartmentDto;
import com.pages.ufazerp.util.exceptions.NotFoundException;
import com.pages.ufazerp.util.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.*;
import static com.pages.ufazerp.util.tools.JsonUtils.*;


@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<Object> getDepartment() {
        try {
            return ok(json("departments", departmentService.readAll().stream().map(GetDepartmentDto::new).collect(Collectors.toList())));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDepartmentById(@PathVariable("id") long id) {
        try {
            return ok(json("department", new GetDepartmentDto(departmentService.readById(id))));
        } catch (NotFoundException e) {
            return status(NOT_FOUND).body(message(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> postDepartment(@RequestBody CreateDepartmentDto dto) {
        try {
            return ok(json("department", new GetDepartmentDto(departmentService.createDepartment(dto))));
        } catch (ValidationException e) {
            return badRequest().body(message(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putDepartment(@PathVariable("id") long id, @RequestBody UpdateDepartmentDto dto) {
        try {
            departmentService.updateDepartment(id, dto);
            return ok().build();
        } catch (ValidationException e) {
            return badRequest().body(message(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDepartment(@PathVariable("id") long id) {
        try {
            departmentService.deleteDepartment(id);
            return ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError().build();
        }
    }
}
