package com.pages.ufazerp.services;

import com.pages.ufazerp.domain.*;
import com.pages.ufazerp.repositories.LessonRepository;
import com.pages.ufazerp.repositories.WeekRepository;
import com.pages.ufazerp.util.dto.lesson.CreateLessonDto;
import com.pages.ufazerp.util.exceptions.NotFoundException;
import com.pages.ufazerp.util.exceptions.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class LessonService {

    private final LessonRepository lessonRepository;
    private final WeekRepository weekRepository;
    private final SubjectService subjectService;
    private final GroupService groupService;
    private final TeacherService teacherService;

    public LessonService(
            LessonRepository lessonRepository,
            WeekRepository weekRepository,
            SubjectService subjectService,
            GroupService groupService,
            TeacherService teacherService) {
        this.lessonRepository = lessonRepository;
        this.weekRepository = weekRepository;
        this.subjectService = subjectService;
        this.groupService = groupService;
        this.teacherService = teacherService;
    }

    public Lesson createLesson(CreateLessonDto dto) throws ValidationException {
        if(dto.getRoom()<=0) {
            throw new ValidationException("Room cannot be equal or less than 0");
        }
        if(dto.getDay()<1 || dto.getDay()>5) {
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
        if(lessonRepository.countLessonsByWeekDayPeriod(week.getNumber(), dto.getDay(), dto.getPeriod()) > 0) {
            throw new ValidationException(String.format("Lesson at week=%d day=%d period=%d exists", week.getNumber(), dto.getDay(), dto.getPeriod()));
        }

        Lesson lesson = new Lesson();
        lesson.setRoom(dto.getRoom());
        lesson.setSubject(subject);
        lesson.setDay(dto.getDay());
        lesson.setGroup(group);
        lesson.setWeek(week);
        lesson.setPeriod(dto.getPeriod());
        lesson.setTeacher(teacher);
        return lessonRepository.save(lesson);
    }

}
