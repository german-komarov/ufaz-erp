package com.pages.ufazerp.util.dto.group;

import com.pages.ufazerp.domain.Group;
import com.pages.ufazerp.util.constants.Level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetGroupDto  {
    private Long id;
    private String name;

    public GetGroupDto(Group group) {
        this.id = group.getGroupId();
        this.name = group.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
