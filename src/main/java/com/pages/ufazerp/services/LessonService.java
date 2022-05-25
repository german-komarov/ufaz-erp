package com.pages.ufazerp.services;

import com.pages.ufazerp.domain.*;
import com.pages.ufazerp.repositories.LessonRepository;
import com.pages.ufazerp.repositories.StudentRepository;
import com.pages.ufazerp.repositories.WeekRepository;
import com.pages.ufazerp.util.dto.lesson.CreateLessonDto;
import com.pages.ufazerp.util.exceptions.NotFoundException;
import com.pages.ufazerp.util.exceptions.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class LessonService {

    private final LessonRepository lessonRepository;
    private final WeekRepository weekRepository;
    private final SubjectService subjectService;
    private final GroupService groupService;
    private final TeacherService teacherService;
    private final StudentRepository studentRepository;

    public LessonService(
            LessonRepository lessonRepository,
            WeekRepository weekRepository,
            SubjectService subjectService,
            GroupService groupService,
            TeacherService teacherService, StudentService studentService, StudentRepository studentRepository) {
        this.lessonRepository = lessonRepository;
        this.weekRepository = weekRepository;
        this.subjectService = subjectService;
        this.groupService = groupService;
        this.teacherService = teacherService;
        this.studentRepository = studentRepository;
    }

    public List<Lesson> readAllLessons() {
        return lessonRepository.findAll();
    }

    public Lesson readById(long id) throws NotFoundException {
        return lessonRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("There is no lesson(id=%d)", id)));
    }

    public List<Student> readAllStudentsOfLesson(long lessonId) {
        return lessonRepository.findAllStudentOfLesson(lessonId);
    }

    public Lesson createLesson(CreateLessonDto dto) throws ValidationException {
        if (dto.getRoom() <= 0) {
            throw new ValidationException("Room cannot be equal or less than 0");
        }
        if (dto.getDay() < 1 || dto.getDay() > 5) {
            throw new ValidationException("Day must be of range [1,5]");
        }
        Subject subject;
        Group group;
        Week week;
        Teacher teacher;
        try {
            subject = subjectService.readById(dto.getSubjectId());
            group = groupService.readById(dto.getGroupId());
            teacher = teacherService.readById(dto.getTeacherId());
            week = weekRepository
                    .findByNumber(dto.getWeek())
                    .orElseThrow(() -> new NotFoundException(String.format("There is no week(number=%d)", dto.getWeek())));
        } catch (NotFoundException e) {
            throw new ValidationException(e.getMessage());
        }
        if (lessonRepository.countLessonsByWeekDayPeriodRoom(week.getNumber(), dto.getDay(), dto.getPeriod(), dto.getRoom()) > 0) {
            throw new ValidationException(String.format("Lesson at week=%d day=%d period=%d room=%d exists", week.getNumber(), dto.getDay(), dto.getPeriod(), dto.getRoom()));
        }
        if(lessonRepository.countLessonsByWeekDayPeriodGroup(week.getNumber(), dto.getDay(), dto.getPeriod(), dto.getGroupId())>0) {
            throw new ValidationException(String.format("Lesson at week=%d day=%d period=%d for group=%d exists", week.getNumber(), dto.getDay(), dto.getPeriod(), dto.getGroupId()));
        }

        Lesson lesson = new Lesson();
        lesson.setRoom(dto.getRoom());
        lesson.setSubject(subject);
        lesson.setDay(dto.getDay());
        lesson.setWeek(week);
        lesson.setPeriod(dto.getPeriod());
        lesson.setDate(week.getStarts().plusDays((lesson.getDay() - 1)));
        lesson.setGroup(group);
        lesson.setTeacher(teacher);
        return lessonRepository.save(lesson);
    }

}
