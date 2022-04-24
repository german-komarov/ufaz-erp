package com.pages.ufazerp.util.dto.group;

import com.pages.ufazerp.util.constants.Level;

public class UpdateGroupDto {
    private String name;
    private Level level;

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

}
