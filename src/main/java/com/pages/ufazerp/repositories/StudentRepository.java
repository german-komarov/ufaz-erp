package com.pages.ufazerp.repositories;

import com.pages.ufazerp.domain.Lesson;
import com.pages.ufazerp.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);

    @Query(value = "select l from Lesson l  where :studentId in (select ls.userId from l.absentStudents ls)")
    List<Lesson> lessonAbsencesByStudentId(long studentId);
}
