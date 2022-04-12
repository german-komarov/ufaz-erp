package com.pages.ufazerp.util.dto.subject;

import com.pages.ufazerp.util.constants.Level;
import com.pages.ufazerp.util.constants.Term;
import com.pages.ufazerp.util.dto.BaseDto;

public class UpdateSubjectDto extends BaseDto {
    private String name;
    private Level level;
    private Term term;
    private Integer credits;
    private Integer totalNumberOfLessons;

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

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Integer getTotalNumberOfLessons() {
        return totalNumberOfLessons;
    }

    public void setTotalNumberOfLessons(Integer totalNumberOfLessons) {
        this.totalNumberOfLessons = totalNumberOfLessons;
    }
}
