package com.pages.ufazerp.util.dto.users.student;

import com.pages.ufazerp.util.dto.users.UpdateUserDto;

public class UpdateStudentDto extends UpdateUserDto {
    private Long groupId;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
