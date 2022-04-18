package com.pages.ufazerp.domain;

import com.pages.ufazerp.util.constants.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher extends User {


    @ManyToMany
    @JoinTable(
            name = "teachers_lessons",
            joinColumns = @JoinColumn(name = "teacher", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "lesson", nullable = false)
    )
    private List<Lesson> lessons = new ArrayList<>();

    @Override
    public Role getRole() {
        return Role.ROLE_TEACHER;
    }
}
