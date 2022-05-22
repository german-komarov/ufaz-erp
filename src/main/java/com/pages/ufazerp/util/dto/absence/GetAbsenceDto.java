package com.pages.ufazerp.util.dto.absence;

import com.pages.ufazerp.domain.Absence;
import com.pages.ufazerp.domain.Student;
import com.pages.ufazerp.util.dto.users.student.GetStudentDto;

import java.util.Date;

public class GetAbsenceDto {
    private long id;
    private GetStudentDto student;
    private Date date;

    public GetAbsenceDto(Absence absence) {
        this.id = absence.getId();
        this.student = new GetStudentDto(absence.getStudent());
        this.date = absence.getDate();
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
