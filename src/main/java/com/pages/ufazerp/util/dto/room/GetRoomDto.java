package com.pages.ufazerp.util.dto.room;

import com.pages.ufazerp.domain.Room;
import com.pages.ufazerp.util.dto.BaseDto;
import com.pages.ufazerp.util.dto.corpus.GetCorpusDto;

public class GetRoomDto extends BaseDto {
    private int id;
    private int number;
    private GetCorpusDto corpus;

    public GetRoomDto(Room room) {
        this.id = Math.toIntExact(room.getId());
        this.number = room.getNumber();
        this.corpus = new GetCorpusDto(room.getCorpus());
        this.createdAt = room.getCreatedAt();
        this.updatedAt = room.getUpdatedAt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public GetCorpusDto getCorpus() {
        return corpus;
    }

    public void setCorpus(GetCorpusDto corpus) {
        this.corpus = corpus;
    }
}
