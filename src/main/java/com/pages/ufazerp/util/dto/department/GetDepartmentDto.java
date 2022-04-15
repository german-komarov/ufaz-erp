package com.pages.ufazerp.util.dto.department;

import com.pages.ufazerp.domain.BaseModel;
import com.pages.ufazerp.domain.Department;

public class GetDepartmentDto extends BaseModel {
    private long id;
    private String name;


    public GetDepartmentDto(Department department) {
        this.id = department.getId();
        this.name = department.getName();
        this.createdAt = department.getCreatedAt();
        this.updatedAt = department.getUpdatedAt();
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
}
