package com.pages.ufazerp.services;

import com.pages.ufazerp.domain.Department;
import com.pages.ufazerp.repositories.DepartmentRepository;
import com.pages.ufazerp.util.dto.department.CreateDepartmentDto;
import com.pages.ufazerp.util.dto.department.UpdateDepartmentDto;
import com.pages.ufazerp.util.exceptions.NotFoundException;
import com.pages.ufazerp.util.exceptions.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> readAll() {
        return departmentRepository.findAll();
    }

    public Department readById(long id) throws NotFoundException {
        return departmentRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("There no department(id=%d)", id)));
    }

    public Department createDepartment(CreateDepartmentDto dto) throws ValidationException {
        if (dto.getName() == null) {
            throw new ValidationException("Name cannot be null");
        }
        if(departmentRepository.findByName(dto.getName()).isPresent()) {
            throw new ValidationException(String.format("department(name=%s) already exists", dto.getName()));
        }

        Department department = new Department();
        department.setName(dto.getName());
        return departmentRepository.save(department);
    }

    public Department updateDepartment(long id, UpdateDepartmentDto dto) throws NotFoundException, ValidationException {
        Department department = readById(id);
        if (dto.getName() == null) {
            throw new ValidationException("Name cannot be null");
        }
        if(departmentRepository.findByName(dto.getName()).isPresent()) {
            throw new ValidationException(String.format("department(name=%s) already exists", dto.getName()));
        }
        department.setName(dto.getName());
        return departmentRepository.save(department);
    }

    public void deleteDepartment(long id) {
        if(!departmentRepository.findById(id).isPresent()) {
            return;
        }
        departmentRepository.deleteById(id);
    }
}
