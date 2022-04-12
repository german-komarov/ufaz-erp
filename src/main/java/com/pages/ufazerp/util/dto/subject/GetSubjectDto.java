package com.pages.ufazerp.util.dto.subject;

import com.pages.ufazerp.domain.BaseModel;
import com.pages.ufazerp.domain.Subject;
import com.pages.ufazerp.util.constants.Level;
import com.pages.ufazerp.util.constants.Term;

public class GetSubjectDto extends BaseModel {
    private long id;
    private String name;
    private Level level;
    private Term term;
    private int credits;
    private int totalNumberOfLessons;

    public GetSubjectDto(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getName();
        this.level = subject.getLevel();
        this.term = subject.getTerm();
        this.credits = subject.getCredits();
        this.totalNumberOfLessons = subject.getTotalNumberOfLessons();
        this.createdAt = subject.getCreatedAt();
        this.updatedAt = subject.getUpdatedAt();
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

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
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
