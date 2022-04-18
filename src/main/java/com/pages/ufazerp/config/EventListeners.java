package com.pages.ufazerp.config;

import com.pages.ufazerp.domain.Admin;
import com.pages.ufazerp.domain.Corpus;
import com.pages.ufazerp.domain.Room;
import com.pages.ufazerp.repositories.CorpusRepository;
import com.pages.ufazerp.repositories.RoomRepository;
import com.pages.ufazerp.repositories.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EventListeners {

    private final CorpusRepository corpusRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public EventListeners(CorpusRepository corpusRepository, RoomRepository roomRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.corpusRepository = corpusRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        if(corpusRepository.findAll().size()>0) {
            return;
        }

        Corpus corpus1 = new Corpus();
        corpus1.setName("main");
        corpus1.setAddress("Nizami 119");
        corpus1 = corpusRepository.save(corpus1);

        Corpus corpus2 = new Corpus();
        corpus2.setName("Lab 1");
        corpus2.setAddress("Nizami 290");
        corpus2 = corpusRepository.save(corpus2);


        for(int i = 1; i<5; i++) {
            for (int j = 100 * i; j<(100*i + 10); j++) {
                Room room = new Room();
                room.setNumber(j);
                room.setCorpus(corpus1);
                roomRepository.save(room);
                room = new Room();
                room.setNumber(j);
                room.setCorpus(corpus2);
                roomRepository.save(room);
            }
        }

        Admin admin = new Admin();
        admin.setEmail("admin@admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setFirstName("Admin");
        admin.setLastName("Admin");
        userRepository.save(admin);
    }


}
