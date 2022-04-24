package com.pages.ufazerp.repositories;

import com.pages.ufazerp.domain.Week;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeekRepository extends JpaRepository<Week, Long> {
    Optional<Week> findByNumber(int number);
}
