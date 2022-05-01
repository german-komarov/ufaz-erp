package com.pages.ufazerp.repositories;

import com.pages.ufazerp.domain.Lesson;
import com.pages.ufazerp.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Query("select count(l) from Lesson l where l.week.number=:week and l.day=:day and l.period=:period and l.room=:room")
    int countLessonsByWeekDayPeriodRoom(int week, int day, int period, int room);

    @Query("select count(l) from Lesson l where l.week.number=:week and l.day=:day and l.period=:period and l.group.groupId=:group")
    int countLessonsByWeekDayPeriodGroup(int week, int day, int period, long group);

    @Query("select l.group.students from Lesson l where l.lessonId=:lessonId")
    List<Student> findAllStudentOfLesson(long lessonId);
}
