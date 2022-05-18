package com.pages.ufazerp.util.dto.users.student;

import com.pages.ufazerp.util.dto.users.UpdateUserDto;

public class UpdateStudentDto extends UpdateUserDto {
    private long groupId;


    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }
}
