package com.pages.ufazerp.domain;

import com.pages.ufazerp.util.constants.Level;
import com.pages.ufazerp.util.constants.Subgroup;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Level level;
    

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Subgroup subgroup;

    @OneToMany(mappedBy = "group")
    private Set<Student> students;

    @ManyToMany
    @JoinTable(
            name = "groups_subjects",
            joinColumns = @JoinColumn(name = "group1", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "subject", nullable = false)
    )
    private Set<Subject> subjects = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "lessons_groups",
            joinColumns = @JoinColumn(name = "lesson", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "group1", nullable = false)
    )
    private Set<Lesson> lessons = new HashSet<>();


    public boolean addSubject(Subject subject) {
        return subjects.add(subject);
    }

    public boolean addSubjects(Set<Subject> inputSubjects) {
        return this.subjects.addAll(inputSubjects);
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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

    public Subgroup getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(Subgroup subgroup) {
        this.subgroup = subgroup;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }
}
