package com.pages.ufazerp.repositories;

import com.pages.ufazerp.domain.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
