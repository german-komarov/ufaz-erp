package com.pages.ufazerp.repositories;

import com.pages.ufazerp.domain.Corpus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CorpusRepository extends JpaRepository<Corpus, Long> {
    Optional<Corpus> findByName(String name);
}
