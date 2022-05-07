package com.pages.ufazerp.util.dto.users.student;

import com.pages.ufazerp.domain.Student;
import com.pages.ufazerp.util.constants.Level;
import com.pages.ufazerp.util.dto.users.GetUserDto;

import java.util.HashMap;
import java.util.Map;

public class GetStudentDto extends GetUserDto {
    public int admissionYear;
    private Map<String, Object> group = new HashMap<>();

    public GetStudentDto(Student student) {
        super(student);
        this.admissionYear = student.getAdmissionYear();
        group.put("id", student.getGroup().getGroupId());
        group.put("name", student.getGroup().getName());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAdmissionYear() {
        return admissionYear;
    }

    public void setAdmissionYear(int admissionYear) {
        this.admissionYear = admissionYear;
    }


    public Map<String, Object> getGroup() {
        return group;
    }

    public void setGroup(Map<String, Object> group) {
        this.group = group;
    }
}
