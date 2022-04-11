package com.pages.ufazerp.services;

import com.pages.ufazerp.domain.User;
import com.pages.ufazerp.repositories.UserRepository;
import com.pages.ufazerp.util.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User readByEmail(String email) throws NotFoundException {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format("There is no user with email=%s", email)));
    }
}
