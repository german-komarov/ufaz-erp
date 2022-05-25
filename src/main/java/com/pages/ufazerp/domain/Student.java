package com.pages.ufazerp.domain;

import com.pages.ufazerp.util.constants.Role;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
public class Student extends User {

    @ManyToOne(optional = false)
    private Group group;

    private int admissionYear;


    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Absence> absences;

    @Override
    public Role getRole() {
        return Role.ROLE_STUDENT;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getAdmissionYear() {
        return admissionYear;
    }

    public void setAdmissionYear(int admissionYear) {
        this.admissionYear = admissionYear;
    }

    public List<Absence> getAbsences() {
        return absences;
    }

    public void setAbsences(List<Absence> absences) {
        this.absences = absences;
    }
}
