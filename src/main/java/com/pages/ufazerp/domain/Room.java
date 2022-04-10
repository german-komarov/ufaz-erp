package com.pages.ufazerp.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;

    @ManyToOne(optional = false)
    private Corpus corpus;

    @OneToMany(mappedBy = "room")
    private Set<Lesson> lessons;
}
