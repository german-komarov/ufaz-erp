package com.pages.ufazerp.repositories;

import com.pages.ufazerp.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, UUID> {
}
