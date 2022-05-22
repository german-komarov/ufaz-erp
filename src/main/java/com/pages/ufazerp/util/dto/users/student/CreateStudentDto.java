package com.pages.ufazerp.util.dto.users.student;

import com.pages.ufazerp.util.dto.users.CreateUserDto;

public class CreateStudentDto extends CreateUserDto {
    private long groupId;
    private int admissionYear;

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
}
