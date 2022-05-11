package com.pages.ufazerp.domain;

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


    @OneToMany(mappedBy = "group")
    private Set<Student> students;

    @ManyToMany
    @JoinTable(
            name = "lessons_groups",
            joinColumns = @JoinColumn(name = "lesson", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "group1", nullable = false)
    )
    private Set<Lesson> lessons = new HashSet<>();

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



    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }


    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }
}
