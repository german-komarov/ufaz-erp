package com.pages.ufazerp.util.dto.absence;

import com.pages.ufazerp.domain.Absence;
import com.pages.ufazerp.util.dto.lesson.GetLessonDto;
import com.pages.ufazerp.util.dto.users.student.GetStudentDto;

public class GetAbsenceDto {
    private long id;
    private GetStudentDto student;
    private GetLessonDto lesson;
    private String date;

    public GetAbsenceDto(Absence absence) {
        this.id = absence.getId();
        this.student = new GetStudentDto(absence.getStudent());
        this.lesson = new GetLessonDto(absence.getLesson());
        this.date = absence.getDate().toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GetStudentDto getStudent() {
        return student;
    }

    public void setStudent(GetStudentDto student) {
        this.student = student;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public GetLessonDto getLesson() {
        return lesson;
    }

    public void setLesson(GetLessonDto lesson) {
        this.lesson = lesson;
    }
}
