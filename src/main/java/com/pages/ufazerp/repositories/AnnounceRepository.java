package com.pages.ufazerp.repositories;

import com.pages.ufazerp.domain.Announce;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnounceRepository extends JpaRepository<Announce, Long> {
}
