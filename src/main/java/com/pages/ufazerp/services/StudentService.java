package com.pages.ufazerp.services;

import com.pages.ufazerp.domain.Group;
import com.pages.ufazerp.domain.Student;
import com.pages.ufazerp.repositories.StudentRepository;
import com.pages.ufazerp.util.dto.users.student.CreateStudentDto;
import com.pages.ufazerp.util.exceptions.NotFoundException;
import com.pages.ufazerp.util.exceptions.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class StudentService {

    private final StudentRepository studentRepository;
    private final GroupService groupService;

    public StudentService(StudentRepository studentRepository, GroupService groupService) {
        this.studentRepository = studentRepository;
        this.groupService = groupService;
    }

    public Student readById(long id) throws NotFoundException {
        return studentRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("There is no user(id=%d)", id)));
    }

    public List<Student> readByAll() {
        return studentRepository.findAll();
    }

    public Student createStudent(CreateStudentDto dto) throws ValidationException {
        if(dto.getEmail()==null) {
            throw new ValidationException("Email cannot be null");
        }
        if(studentRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new ValidationException("Email already exists");
        }
        if(dto.getFirstName()==null) {
            throw new ValidationException("First name cannot be null");
        }
        if(dto.getLastName()==null) {
            throw new ValidationException("Last name cannot be null");
        }
        if(dto.getLevel()==null) {
            throw new ValidationException("Level cannot be null");
        }
        if(dto.getAdmissionYear()<2000) {
            throw new ValidationException("Admission year cannot be earlier 2000");
        }
        Group group;
        try {
            group = groupService.readById(dto.getGroupId());
        } catch (NotFoundException e) {
            throw new ValidationException(e.getMessage());
        }
        Student student = new Student();
        student.setEmail(dto.getEmail());
        student.setPassword(dto.getPassword());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setAdmissionYear(dto.getAdmissionYear());
        student.setLevel(dto.getLevel());
        student.setGroup(group);
        return studentRepository.save(student);
    }


}
