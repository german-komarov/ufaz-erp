package com.pages.ufazerp.services;

import com.pages.ufazerp.domain.Group;
import com.pages.ufazerp.domain.Lesson;
import com.pages.ufazerp.domain.Student;
import com.pages.ufazerp.domain.Subject;
import com.pages.ufazerp.repositories.StudentRepository;
import com.pages.ufazerp.util.dto.users.student.CreateStudentDto;
import com.pages.ufazerp.util.dto.users.student.UpdateStudentDto;
import com.pages.ufazerp.util.exceptions.NotFoundException;
import com.pages.ufazerp.util.exceptions.ValidationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class StudentService {

    private final StudentRepository studentRepository;
    private final GroupService groupService;
    private final BCryptPasswordEncoder passwordEncoder;

    public StudentService(StudentRepository studentRepository, GroupService groupService, BCryptPasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.groupService = groupService;
        this.passwordEncoder = passwordEncoder;
    }

    public Student readById(long id) throws NotFoundException {
        return studentRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("There is no user(id=%d)", id)));
    }

    public List<Student> readAllById(Iterable<Long> ids) {
        return studentRepository.findAllById(ids);
    }

    public List<Student> readAll() {
        return studentRepository.findAll();
    }

    public List<Lesson> readAllLessonsByStudentId(long id) throws NotFoundException {
        Student student = readById(id);
        return student.getGroup().getLessons();
    }

    public Set<Subject> readAllSubjects(long id) throws NotFoundException {
        Student student = readById(id);
        Group group = student.getGroup();
        return group.getLessons().stream().map(Lesson::getSubject).collect(Collectors.toSet());
    }

    public List<Student> readAllStudentsById(Iterable<Long> ids) {
        return studentRepository.findAllById(ids);
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
        student.setGroup(group);
        return studentRepository.save(student);
    }

    public Student updateStudent(long id, UpdateStudentDto dto) throws NotFoundException, ValidationException {
        Student student = readById(id);
        if(dto.getEmail()!=null) {
            if(!student.getEmail().equals(dto.getEmail()) && studentRepository.findByEmail(dto.getEmail()).isPresent()) {
                throw new ValidationException(String.format("User(email=%s) already exists", dto.getEmail()));
            }
            student.setEmail(dto.getEmail());
        }
        if(dto.getPassword()!=null) {
            student.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        if(dto.getFirstName()!=null) {
            student.setFirstName(dto.getFirstName());
        }
        if(dto.getLastName()!=null) {
            student.setLastName(dto.getLastName());
        }
        if(dto.getGroupId()!=null) {
            Group group;
            try {
                group = groupService.readById(dto.getGroupId());
            } catch (NotFoundException e) {
                throw new ValidationException(e.getMessage());
            }
            student.setGroup(group);
        }
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        if(!studentRepository.findById(id).isPresent()) {
            return;
        }
        studentRepository.deleteById(id);
    }

}
