package com.pages.ufazerp.repositories;

import com.pages.ufazerp.domain.Absence;
import com.pages.ufazerp.domain.Lesson;
import com.pages.ufazerp.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AbsenceRepository extends JpaRepository<Absence, Long> {
    List<Absence> findAllByLesson(Lesson lesson);
    List<Absence> findAllByStudent(Student student);
}
