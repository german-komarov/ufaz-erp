package com.pages.ufazerp.util.dto.absence;

import java.util.List;

public class CreateAbsencesDto {
    private long lessonId;
    List<Long> students;

    public List<Long> getStudents() {
        return students;
    }

    public void setStudents(List<Long> students) {
        this.students = students;
    }

    public long getLessonId() {
        return lessonId;
    }

    public void setLessonId(long lessonId) {
        this.lessonId = lessonId;
    }
}
