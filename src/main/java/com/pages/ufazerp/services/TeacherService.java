package com.pages.ufazerp.services;

import com.pages.ufazerp.domain.Teacher;
import com.pages.ufazerp.repositories.TeacherRepository;
import com.pages.ufazerp.util.dto.users.teacher.CreateTeacherDto;
import com.pages.ufazerp.util.dto.users.teacher.UpdateTeacherDto;
import com.pages.ufazerp.util.exceptions.NotFoundException;
import com.pages.ufazerp.util.exceptions.ValidationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public TeacherService(TeacherRepository teacherRepository, BCryptPasswordEncoder passwordEncoder) {
        this.teacherRepository = teacherRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Teacher> readAll() {
        return teacherRepository.findAll();
    }

    public Teacher readById(long id) throws NotFoundException {
        return teacherRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("There is no teacher(id=%d)", id)));
    }

    public Teacher createTeacher(CreateTeacherDto dto) throws ValidationException {
        if(dto.getEmail()==null) {
            throw new ValidationException("Email cannot be null");
        }
        if(teacherRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new ValidationException("Email already exists");
        }
        if(dto.getFirstName()==null) {
            throw new ValidationException("First name cannot be null");
        }
        if(dto.getLastName()==null) {
            throw new ValidationException("Last name cannot be null");
        }
        Teacher teacher = new Teacher();
        teacher.setEmail(dto.getEmail());
        teacher.setPassword(passwordEncoder.encode(dto.getPassword()));
        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(long id, UpdateTeacherDto dto) throws NotFoundException, ValidationException {
        Teacher teacher = readById(id);
        if(dto.getEmail()!=null) {
            if(!teacher.getEmail().equals(dto.getEmail()) && teacherRepository.findByEmail(dto.getEmail()).isPresent()) {
                throw new ValidationException(String.format("User(email=%s) already exists", dto.getEmail()));
            }
            teacher.setEmail(dto.getEmail());
        }
        if(dto.getPassword()!=null) {
            teacher.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        if(dto.getFirstName()!=null) {
            teacher.setFirstName(dto.getFirstName());
        }
        if(dto.getLastName()!=null) {
            teacher.setLastName(dto.getLastName());
        }
        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(long id) {
        if(!teacherRepository.findById(id).isPresent()) {
            return;
        }
        teacherRepository.deleteById(id);
    }
}
