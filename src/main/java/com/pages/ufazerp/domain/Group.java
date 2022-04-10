package com.pages.ufazerp.domain;

import com.pages.ufazerp.util.constants.Level;
import com.pages.ufazerp.util.constants.Subgroup;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Level level;

    @ManyToOne
    private Speciality speciality;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Subgroup subgroup;

    @OneToMany(mappedBy = "group")
    private Set<Student> students;

    @ManyToMany
    @JoinTable(
            name = "groups_subjects",
            joinColumns = @JoinColumn(name = "group1", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "subject", nullable = false)
    )
    private Set<Subject> subjects = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "lessons_groups")
    private Set<Lesson> lessons = new HashSet<>();
}
