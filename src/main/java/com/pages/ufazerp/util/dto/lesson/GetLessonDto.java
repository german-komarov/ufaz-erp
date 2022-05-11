package com.pages.ufazerp.util.dto.lesson;

import com.pages.ufazerp.domain.Lesson;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GetLessonDto {
    private long lessonId;
    private int room;
    private int week;
    private int day;
    private int period;
    private Date date;
    private Map<String, Object> subject = new HashMap<>();
    private Map<String, Object> teacher = new HashMap<>();
    private Map<String , Object> group = new HashMap<>();


    public GetLessonDto(Lesson lesson) {
        this.lessonId = lesson.getLessonId();
        this.room = lesson.getRoom();
        this.week = lesson.getWeek().getNumber();
        this.day = lesson.getDay();
        this.period = lesson.getPeriod();
        this.date = Date.from(Instant.from(lesson.getDate()));
        subject.put("id", lesson.getSubject().getId());
        subject.put("name", lesson.getSubject().getName());
        teacher.put("id", lesson.getTeacher().getUserId());
        teacher.put("firstName", lesson.getTeacher().getFirstName());
        teacher.put("lastName", lesson.getTeacher().getLastName());
        group.put("id", lesson.getGroup().getGroupId());
        group.put("name", lesson.getGroup().getName());
    }


    public long getLessonId() {
        return lessonId;
    }

    public void setLessonId(long lessonId) {
        this.lessonId = lessonId;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Map<String, Object> getSubject() {
        return subject;
    }

    public void setSubject(Map<String, Object> subject) {
        this.subject = subject;
    }

    public Map<String, Object> getTeacher() {
        return teacher;
    }

    public void setTeacher(Map<String, Object> teacher) {
        this.teacher = teacher;
    }

    public Map<String, Object> getGroup() {
        return group;
    }

    public void setGroup(Map<String, Object> group) {
        this.group = group;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
