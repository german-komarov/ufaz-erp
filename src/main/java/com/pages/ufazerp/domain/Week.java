package com.pages.ufazerp.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "weeks")
public class Week {

    @Id
    private int number;
    private LocalDate starts;
    private LocalDate ends;

    @OneToMany(mappedBy = "week")
    private List<Lesson> lessons;


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public LocalDate getStarts() {
        return starts;
    }

    public void setStarts(LocalDate starts) {
        this.starts = starts;
    }

    public LocalDate getEnds() {
        return ends;
    }

    public void setEnds(LocalDate ends) {
        this.ends = ends;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    @PrePersist
    private void onPersist() {
        this.ends = this.starts.plusDays(4);
    }
}
