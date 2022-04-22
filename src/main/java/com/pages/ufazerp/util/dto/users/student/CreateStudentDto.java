package com.pages.ufazerp.util.dto.users.student;

import com.pages.ufazerp.util.constants.Level;
import com.pages.ufazerp.util.dto.users.CreateUserDto;

public class CreateStudentDto extends CreateUserDto {
    private long groupId;
    private int admissionYear;
    private Level level;

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
