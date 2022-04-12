package com.pages.ufazerp.util.dto.corpus;

import com.pages.ufazerp.domain.Corpus;
import com.pages.ufazerp.util.dto.BaseDto;

import java.util.Date;

public class GetCorpusDto extends BaseDto {
    private Long id;
    private String name;
    private String address;

    public GetCorpusDto(
            Corpus corpus
    ) {
        this.id = corpus.getId();
        this.name = corpus.getName();
        this.address = corpus.getAddress();
        this.createdAt = corpus.getCreatedAt();
        this.updateAt = corpus.getUpdatedAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
