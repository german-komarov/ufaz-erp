package com.pages.ufazerp.util.dto.group;

import com.pages.ufazerp.domain.BaseModel;
import com.pages.ufazerp.domain.Group;
import com.pages.ufazerp.util.constants.Level;
import com.pages.ufazerp.util.constants.Subgroup;
import com.pages.ufazerp.util.dto.subject.GetSubjectDto;
import com.pages.ufazerp.util.dto.users.student.GetStudentDto;

import java.util.List;
import java.util.stream.Collectors;

public class GetGroupDto extends BaseModel {
    private String name;
    private Level level;
    private Subgroup subgroup;
    private List<GetStudentDto> students;
    private List<GetSubjectDto> subjects;

    public GetGroupDto(Group group) {
        this.name = group.getName();
        this.level = group.getLevel();
        this.subgroup = group.getSubgroup();
        if(!group.getStudents().isEmpty()) {
            this.students = group.getStudents().stream().map(GetStudentDto::new).collect(Collectors.toList());
        }
        if(!group.getSubjects().isEmpty()) {
            this.subjects = group.getSubjects().stream().map(GetSubjectDto::new).collect(Collectors.toList());
        }
    }
}
