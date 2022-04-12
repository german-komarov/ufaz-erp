package com.pages.ufazerp.util.dto.subject;

import com.pages.ufazerp.util.constants.Level;
import com.pages.ufazerp.util.constants.Term;
import com.pages.ufazerp.util.dto.BaseDto;

public class CreateSubjectDto extends BaseDto {
    private String name;
    private Level level;
    private Term term;
    private int credits;
    private int totalNumberOfLessons;

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
