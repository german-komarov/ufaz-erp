package com.pages.ufazerp.domain;

import com.pages.ufazerp.util.constants.Level;
import com.pages.ufazerp.util.constants.Subgroup;
import com.pages.ufazerp.util.constants.Term;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lessons")
public class Lesson extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessonId;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Room room;

    private boolean isMagisterial;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Level level;

    @ManyToMany
    @JoinTable(name = "teachers_lessons")
    private Set<Teacher> teachers = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "lessons_groups",
            joinColumns = @JoinColumn(name = "lesson", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "group1", nullable = false)
    )
    private Set<Group> groups = new HashSet<>();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Term term;
    private int week;
    private int day;

    @ElementCollection
    @CollectionTable(name = "lessons_periods", joinColumns = @JoinColumn(name = "lesson"))
    @Column(name = "period")
    private Set<Integer> periods;
}
