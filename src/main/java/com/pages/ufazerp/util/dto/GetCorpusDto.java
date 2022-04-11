package com.pages.ufazerp.util.dto;

import java.util.Date;

public class GetCorpusDto extends BaseDto {
    private Long id;
    private String name;
    private String address;

    public GetCorpusDto(
            Long id,
            String name,
            String address,
            Date createdAt,
            Date updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.createdAt = createdAt;
        this.updateAt = updatedAt;
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
