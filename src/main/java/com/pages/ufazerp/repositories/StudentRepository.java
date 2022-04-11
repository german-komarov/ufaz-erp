package com.pages.ufazerp.repositories;

import com.pages.ufazerp.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
}
