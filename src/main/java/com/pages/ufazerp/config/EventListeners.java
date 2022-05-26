package com.pages.ufazerp.config;

import com.pages.ufazerp.domain.*;
import com.pages.ufazerp.repositories.*;
import com.pages.ufazerp.services.LessonService;
import com.pages.ufazerp.util.dto.lesson.CreateOrUpdateLessonDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class EventListeners {

    @Value("${test-data}")
    private boolean isTestData;

    private final WeekRepository weekRepository;
    private final AdminRepository adminRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final SubjectRepository subjectRepository;
    private final AnnounceRepository announceRepository;
    private final GroupRepository groupRepository;
    private final LessonService lessonService;

    public EventListeners(
            WeekRepository weekRepository,
            AdminRepository adminRepository,
            TeacherRepository teacherRepository,
            StudentRepository studentRepository,
            BCryptPasswordEncoder passwordEncoder, SubjectRepository subjectRepository, AnnounceRepository announceRepository, GroupRepository groupRepository, LessonService lessonService) {
        this.weekRepository = weekRepository;
        this.adminRepository = adminRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
        this.subjectRepository = subjectRepository;
        this.announceRepository = announceRepository;
        this.groupRepository = groupRepository;
        this.lessonService = lessonService;
    }


    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        if(isTestData) {
            if(!adminRepository.findAll().isEmpty()) {
                return;
            }

            Admin admin = new Admin();
            admin.setEmail("admin@admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setFirstName("Admin");
            admin.setLastName("Admin");
            adminRepository.save(admin);


            Group group1 = new Group();
            group1.setName("CS-19");
            groupRepository.save(group1);

            Group group2 = new Group();
            group2.setName("CE-19");
            groupRepository.save(group2);

            Student student1 = new Student();
            student1.setEmail("student1@student");
            student1.setPassword(passwordEncoder.encode("student"));
            student1.setFirstName("Student1");
            student1.setLastName("Student1");
            student1.setGroup(group1);
            student1.setAdmissionYear(2019);
            studentRepository.save(student1);


            Student student2 = new Student();
            student2.setEmail("student2@student");
            student2.setPassword(passwordEncoder.encode("student"));
            student2.setFirstName("Student2");
            student2.setLastName("Student2");
            student2.setGroup(group2);
            student2.setAdmissionYear(2019);
            studentRepository.save(student2);

            Student student3 = new Student();
            student3.setEmail("student3@student");
            student3.setPassword(passwordEncoder.encode("student"));
            student3.setFirstName("Student3");
            student3.setLastName("Student3");
            student3.setGroup(group2);
            student3.setAdmissionYear(2019);
            studentRepository.save(student3);


            Teacher teacher1 = new Teacher();
            teacher1.setEmail("teacher1@teacher");
            teacher1.setPassword(passwordEncoder.encode("teacher"));
            teacher1.setFirstName("Teacher 1");
            teacher1.setLastName("Teacher 1");
            teacherRepository.save(teacher1);

            Teacher teacher2 = new Teacher();
            teacher2.setEmail("teacher2@teacher");
            teacher2.setPassword(passwordEncoder.encode("teacher"));
            teacher2.setFirstName("Teacher 2");
            teacher2.setLastName("Teacher 2");
            teacherRepository.save(teacher2);


            Subject math = new Subject();
            math.setName("Math 1");
            math.setCredits(6);
            math.setTotalNumberOfLessons(24);
            subjectRepository.save(math);

            Subject physics = new Subject();
            physics.setName("Physics 1");
            physics.setCredits(4);
            physics.setTotalNumberOfLessons(18);
            subjectRepository.save(physics);

            Subject english = new Subject();
            english.setName("English 1");
            english.setCredits(4);
            english.setTotalNumberOfLessons(65);
            subjectRepository.save(english);


            Announce announce1 = new Announce();
            announce1.setAuthor(teacher1);
            announce1.setTitle("Announce Title 1");
            announce1.setText("Announce Text 1");
            announce1.setPublishDate(LocalDateTime.now().plusDays(5));
            announceRepository.save(announce1);

            Announce announce2 = new Announce();
            announce2.setAuthor(teacher2);
            announce2.setTitle("Announce Title 2");
            announce2.setText("Announce Text 2");
            announce2.setPublishDate(LocalDateTime.now().plusDays(7));
            announceRepository.save(announce2);

            Announce announce3 = new Announce();
            announce3.setAuthor(student3);
            announce3.setTitle("Announce Title 3");
            announce3.setText("Announce Text 3");
            announce3.setPublishDate(LocalDateTime.now().plusDays(29));
            announceRepository.save(announce3);

            Announce announce4 = new Announce();
            announce4.setAuthor(student1);
            announce4.setTitle("Announce Title 4");
            announce4.setText("Announce Text 4");
            announce4.setPublishDate(LocalDateTime.now().plusDays(1));
            announceRepository.save(announce4);


            LocalDate termStarts = LocalDate.ofYearDay(2022, 24);
            for(int i=0;i<20;i++) {
                Week week = new Week();
                week.setNumber(i+1);
                week.setStarts(termStarts.plusDays(i*7));
                weekRepository.save(week);
            }

            try {
                CreateOrUpdateLessonDto lesson1 = new CreateOrUpdateLessonDto();
                lesson1.setDay(3);
                lesson1.setGroupId(group1.getGroupId());
                lesson1.setPeriod(2);
                lesson1.setRoom(306);
                lesson1.setWeek(1);
                lesson1.setSubjectId(math.getId());
                lesson1.setTeacherId(teacher1.getUserId());
                lessonService.createLesson(lesson1);

                CreateOrUpdateLessonDto lesson2 = new CreateOrUpdateLessonDto();
                lesson2.setDay(2);
                lesson2.setGroupId(group1.getGroupId());
                lesson2.setPeriod(5);
                lesson2.setRoom(206);
                lesson2.setWeek(2);
                lesson2.setSubjectId(physics.getId());
                lesson2.setTeacherId(teacher2.getUserId());
                lessonService.createLesson(lesson2);

                CreateOrUpdateLessonDto lesson3 = new CreateOrUpdateLessonDto();
                lesson3.setDay(2);
                lesson3.setGroupId(group1.getGroupId());
                lesson3.setPeriod(3);
                lesson3.setRoom(106);
                lesson3.setWeek(1);
                lesson3.setSubjectId(english.getId());
                lesson3.setTeacherId(teacher1.getUserId());
                lessonService.createLesson(lesson3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
