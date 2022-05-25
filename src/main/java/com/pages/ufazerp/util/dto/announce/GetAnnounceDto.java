package com.pages.ufazerp.util.dto.announce;

import com.pages.ufazerp.domain.Announce;
import com.pages.ufazerp.util.dto.users.GetUserDto;

public class GetAnnounceDto {
    private Long id;
    private String title;
    private String text;
    private String publishDate;
    private GetUserDto author;

    public GetAnnounceDto(Announce announce) {
        this.id = announce.getId();
        this.title = announce.getTitle();
        this.text = announce.getText();
        this.publishDate = announce.getPublishDate().toString();
        this.author = new GetUserDto(announce.getAuthor());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public GetUserDto getAuthor() {
        return author;
    }

    public void setAuthor(GetUserDto author) {
        this.author = author;
    }
}
