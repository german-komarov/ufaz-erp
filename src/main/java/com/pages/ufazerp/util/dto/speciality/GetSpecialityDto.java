package com.pages.ufazerp.util.dto.speciality;

import com.pages.ufazerp.domain.BaseModel;
import com.pages.ufazerp.domain.Department;
import com.pages.ufazerp.domain.Speciality;
import com.pages.ufazerp.util.dto.department.GetDepartmentDto;

public class GetSpecialityDto extends BaseModel {
    private long id;
    private String name;
    private GetDepartmentDto department;


    public GetSpecialityDto(Speciality speciality) {
        this.id = speciality.getId();
        this.name = speciality.getName();
        this.department = new GetDepartmentDto(speciality.getDepartment());
        this.createdAt = speciality.getCreatedAt();
        this.updatedAt = speciality.getUpdatedAt();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GetDepartmentDto getDepartment() {
        return department;
    }

    public void setDepartment(GetDepartmentDto department) {
        this.department = department;
    }
}
