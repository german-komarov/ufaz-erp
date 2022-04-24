package com.pages.ufazerp.config;

import com.pages.ufazerp.domain.Admin;
import com.pages.ufazerp.domain.Week;
import com.pages.ufazerp.repositories.UserRepository;
import com.pages.ufazerp.repositories.WeekRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EventListeners {
    private final UserRepository userRepository;
    private final WeekRepository weekRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public EventListeners(
            UserRepository userRepository,
            WeekRepository weekRepository,
            BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.weekRepository = weekRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        if(userRepository.findByEmail("admin@admin").isPresent()) {
            return;
        }

        Admin admin = new Admin();
        admin.setEmail("admin@admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setFirstName("Admin");
        admin.setLastName("Admin");
        userRepository.save(admin);

        LocalDate termStarts = LocalDate.ofYearDay(2022, 24);
        for(int i=0;i<20;i++) {
            Week week = new Week();
            week.setNumber(i+1);
            week.setStarts(termStarts.plusDays(i*7));
            weekRepository.save(week);
        }
    }


}
