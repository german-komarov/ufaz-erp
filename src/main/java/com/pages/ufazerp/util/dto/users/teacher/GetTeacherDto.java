package com.pages.ufazerp.util.dto.users.teacher;

import com.pages.ufazerp.domain.Teacher;
import com.pages.ufazerp.util.dto.users.GetUserDto;

public class GetTeacherDto extends GetUserDto {
    public GetTeacherDto(Teacher teacher) {
        super(teacher);
    }
}
