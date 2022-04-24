package com.pages.ufazerp.util.dto.group;

import com.pages.ufazerp.util.constants.Level;

import java.util.Set;

public class CreateGroupDto {
    private String name;
    private Level level;

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


    public Set<Long> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Long> subjects) {
        this.subjects = subjects;
    }
}
