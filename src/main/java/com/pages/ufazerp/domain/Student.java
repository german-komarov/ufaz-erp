package com.pages.ufazerp.domain;

import com.pages.ufazerp.util.constants.Level;
import com.pages.ufazerp.util.constants.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends User {

    @ManyToOne(optional = false)
    private Group group;

    private int admissionYear;

    @Column(nullable = false)
    private Level level;

    @Override
    public Role getRole() {
        return Role.ROLE_STUDENT;
    }
}
