package com.pages.ufazerp.util.dto.subject;

import com.pages.ufazerp.util.constants.Level;

public class UpdateSubjectDto {
    private String name;
    private Integer credits;
    private Integer totalNumberOfLessons;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
