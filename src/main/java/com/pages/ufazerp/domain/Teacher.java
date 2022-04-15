package com.pages.ufazerp.domain;

import com.pages.ufazerp.util.constants.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher extends User {

    @ManyToOne(optional = false)
    private Department department;

    @ManyToMany
    @JoinTable(
            name = "teachers_lessons",
            joinColumns = @JoinColumn(name = "teacher", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "lesson", nullable = false)
    )
    private List<Lesson> lessons = new ArrayList<>();

    @Override
    public Role getRole() {
        return Role.TEACHER;
    }
}
