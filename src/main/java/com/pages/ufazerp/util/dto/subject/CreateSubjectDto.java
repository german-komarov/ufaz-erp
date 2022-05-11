package com.pages.ufazerp.util.dto.subject;

import com.pages.ufazerp.util.constants.Level;

public class CreateSubjectDto {
    private String name;
    private int credits;
    private int totalNumberOfLessons;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
