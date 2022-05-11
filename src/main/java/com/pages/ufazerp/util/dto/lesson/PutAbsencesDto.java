package com.pages.ufazerp.util.dto.lesson;

import java.util.List;

public class PutAbsencesDto {
    List<Long> students;

    public List<Long> getStudents() {
        return students;
    }

    public void setStudents(List<Long> students) {
        this.students = students;
    }
}
