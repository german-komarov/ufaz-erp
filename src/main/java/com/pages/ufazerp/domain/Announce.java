package com.pages.ufazerp.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "announces")
public class Announce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User author;
    @Column(nullable = false)
    private String title;
    private String text;
    private Date publishDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date date) {
        this.publishDate = date;
    }
}
