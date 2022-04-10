package com.pages.ufazerp.domain;

import com.pages.ufazerp.util.constants.Role;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends User {

    @ManyToOne
    private Group group;


    @Override
    public Role getRole() {
        return Role.STUDENT;
    }
}
