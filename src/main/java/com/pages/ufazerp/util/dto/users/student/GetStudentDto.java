package com.pages.ufazerp.util.dto.users.student;

import com.pages.ufazerp.domain.BaseModel;
import com.pages.ufazerp.domain.Student;
import com.pages.ufazerp.util.constants.Level;
import com.pages.ufazerp.util.dto.group.GetGroupDto;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GetStudentDto extends BaseModel {
    private long id;
    private String firstName;
    private String lastName;
    public int admissionYear;
    private Level level;
    private Map<String, Object> group = new HashMap<>();

    public GetStudentDto(Student student) {
        this.id = student.getUserId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.admissionYear = student.getAdmissionYear();
        this.level = student.getLevel();
        group.put("id", student.getGroup().getGroupId());
        group.put("name", student.getGroup().getName());
        this.createdAt = student.getCreatedAt();
        this.updatedAt = student.getUpdatedAt();
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

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Map<String, Object> getGroup() {
        return group;
    }

    public void setGroup(Map<String, Object> group) {
        this.group = group;
    }
}
