package com.pages.ufazerp;

import com.pages.ufazerp.domain.Admin;
import com.pages.ufazerp.domain.User;
import com.pages.ufazerp.repositories.UserRepository;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class UfazErpApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    void contextLoads() {
        Admin admin = new Admin();
        admin.setPassword(encoder.encode("password"));
        admin.setEmail("hello@example");
        admin.setFirstName("hello");
        admin.setLastName("hello");
        userRepository.save(admin);
    }

}
