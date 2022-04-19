package com.pages.ufazerp.repositories;

import com.pages.ufazerp.domain.User;
import org.aspectj.apache.bcel.generic.LOOKUPSWITCH;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
