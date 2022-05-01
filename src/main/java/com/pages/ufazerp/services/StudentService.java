package com.pages.ufazerp.services;

import com.pages.ufazerp.domain.Group;
import com.pages.ufazerp.domain.Lesson;
import com.pages.ufazerp.domain.Student;
import com.pages.ufazerp.domain.User;
import com.pages.ufazerp.repositories.StudentRepository;
import com.pages.ufazerp.util.dto.users.student.CreateStudentDto;
import com.pages.ufazerp.util.exceptions.NotFoundException;
import com.pages.ufazerp.util.exceptions.ValidationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class StudentService {

    private final StudentRepository studentRepository;
    private final GroupService groupService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final LessonService lessonService;

    public StudentService(StudentRepository studentRepository, GroupService groupService, BCryptPasswordEncoder passwordEncoder, LessonService lessonService) {
        this.studentRepository = studentRepository;
        this.groupService = groupService;
        this.passwordEncoder = passwordEncoder;
        this.lessonService = lessonService;
    }

    public Student readById(long id) throws NotFoundException {
        return studentRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("There is no user(id=%d)", id)));
    }

    public List<Student> readAll() {
        return studentRepository.findAll();
    }


    public List<Lesson> readAllAbsences() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        User user = (User) securityContext.getAuthentication().getPrincipal();
        return studentRepository.lessonAbsencesByStudentId(user.getUserId());
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
        student.setPassword(passwordEncoder.encode(dto.getPassword()));
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setAdmissionYear(dto.getAdmissionYear());
        student.setLevel(dto.getLevel());
        student.setGroup(group);
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        if(!studentRepository.findById(id).isPresent()) {
            return;
        }
        studentRepository.deleteById(id);
    }

}
