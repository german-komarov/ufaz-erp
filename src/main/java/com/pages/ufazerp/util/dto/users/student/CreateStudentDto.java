package com.pages.ufazerp.util.dto.users.student;

import com.pages.ufazerp.util.constants.Level;

public class CreateStudentDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private long groupId;
    private int admissionYear;
    private Level level;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
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
}
