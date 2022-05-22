package com.pages.ufazerp.domain;

import com.pages.ufazerp.util.constants.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher extends User {


    @OneToMany(mappedBy = "teacher")
    private List<Lesson> lessons = new ArrayList<>();

    @Override
    public Role getRole() {
        return Role.ROLE_TEACHER;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
