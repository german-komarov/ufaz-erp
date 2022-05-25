package com.pages.ufazerp.services;

import com.pages.ufazerp.domain.Absence;
import com.pages.ufazerp.domain.Lesson;
import com.pages.ufazerp.domain.Student;
import com.pages.ufazerp.repositories.AbsenceRepository;
import com.pages.ufazerp.util.dto.absence.CreateAbsencesDto;
import com.pages.ufazerp.util.exceptions.NotFoundException;
import com.pages.ufazerp.util.exceptions.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AbsenceService {
    private final AbsenceRepository absenceRepository;
    private final LessonService lessonService;
    private final StudentService studentService;

    public AbsenceService(AbsenceRepository absenceRepository, LessonService lessonService, StudentService studentService) {
        this.absenceRepository = absenceRepository;
        this.lessonService = lessonService;
        this.studentService = studentService;
    }

    public List<Absence> readAll() {
        return absenceRepository.findAll();
    }

    public List<Absence> readAllByStudentId(long id) throws NotFoundException {
        Student student = studentService.readById(id);
        return absenceRepository.findAllByStudent(student);
    }

    public List<Absence> readAllByLessonId(long id) throws NotFoundException {
        Lesson lesson = lessonService.readById(id);
        return absenceRepository.findAllByLesson(lesson);
    }

    public List<Absence> createAbsences(CreateAbsencesDto dto) throws ValidationException {
        Lesson lesson;
        List<Student> students;
        try {
            lesson = lessonService.readById(dto.getLessonId());
            students = studentService.readAllStudentsById(dto.getStudents());
        } catch (NotFoundException e) {
            throw new ValidationException(e.getMessage());
        }
        List<Absence> absences = new ArrayList<>();
        students.forEach(student -> {
            Absence absence = new Absence();
            absence.setLesson(lesson);
            absence.setStudent(student);
            absence.setDate(LocalDate.now());
            absences.add(absence);
        });
        return absenceRepository.saveAll(absences);
    }

    public void deleteAbsence(long id) {
        if(!absenceRepository.findById(id).isPresent()) {
            return;
        }
        absenceRepository.deleteById(id);
    }
}
