package com.pages.ufazerp.repositories;

import com.pages.ufazerp.domain.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Query("select count(l) from Lesson l where l.week.number=:week and l.day=:day and l.period=:period")
    int countLessonsByWeekDayPeriod(int week, int day, int period);
}
