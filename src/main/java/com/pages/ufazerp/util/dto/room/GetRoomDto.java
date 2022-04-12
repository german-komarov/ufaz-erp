package com.pages.ufazerp.util.dto.room;

import com.pages.ufazerp.domain.Room;
import com.pages.ufazerp.util.dto.corpus.GetCorpusDto;

public class GetRoomDto {
    private int number;
    private GetCorpusDto corpus;

    public GetRoomDto(Room room) {
        this.number = room.getNumber();
        this.corpus = new GetCorpusDto(room.getCorpus());
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
