package com.pages.ufazerp.domain;

import com.pages.ufazerp.util.constants.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher extends User {

    @ManyToMany
    @JoinTable(
            name = "teachers_lessons",
            joinColumns = @JoinColumn(name = "teacher", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "lesson", nullable = false)
    )
    private Set<Lesson> lessons = new HashSet<>();


    @Override
    public Role getRole() {
        return Role.TEACHER;
    }
}
