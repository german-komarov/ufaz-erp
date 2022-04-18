package com.pages.ufazerp.util.dto.group;

import com.pages.ufazerp.util.constants.Level;
import com.pages.ufazerp.util.constants.Subgroup;

import java.util.Set;

public class CreateGroupDto {
    private String name;
    private Level level;
    private Subgroup subgroup;
    private Set<Long> subjects;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Subgroup getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(Subgroup subgroup) {
        this.subgroup = subgroup;
    }

    public Set<Long> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Long> subjects) {
        this.subjects = subjects;
    }
}
