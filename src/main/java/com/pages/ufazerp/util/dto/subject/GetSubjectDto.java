package com.pages.ufazerp.util.dto.subject;

import com.pages.ufazerp.domain.Subject;
import com.pages.ufazerp.util.constants.Level;

public class GetSubjectDto  {
    private long id;
    private String name;
    private Level level;
    private int credits;
    private int totalNumberOfLessons;

    public GetSubjectDto(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getName();
        this.level = subject.getLevel();
        this.credits = subject.getCredits();
        this.totalNumberOfLessons = subject.getTotalNumberOfLessons();
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

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }


    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getTotalNumberOfLessons() {
        return totalNumberOfLessons;
    }

    public void setTotalNumberOfLessons(int totalNumberOfLessons) {
        this.totalNumberOfLessons = totalNumberOfLessons;
    }
}
