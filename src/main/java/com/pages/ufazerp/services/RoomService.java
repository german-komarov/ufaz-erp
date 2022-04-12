package com.pages.ufazerp.services;

import com.pages.ufazerp.domain.Room;
import com.pages.ufazerp.repositories.RoomRepository;
import com.pages.ufazerp.util.dto.room.CreateRoomDto;
import com.pages.ufazerp.util.dto.room.UpdateRoomDto;
import com.pages.ufazerp.util.exceptions.NotFoundException;
import com.pages.ufazerp.util.exceptions.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoomService {

    private final RoomRepository roomRepository;
    private final CorpusService corpusService;

    public RoomService(RoomRepository roomRepository, CorpusService corpusService) {
        this.roomRepository = roomRepository;
        this.corpusService = corpusService;
    }

    public List<Room> readAll() {
        return roomRepository.findAll();
    }

    public Room readById(long id) throws NotFoundException {
        return roomRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("There is no room(id=%d)", id)));
    }

    public Room createRoom(CreateRoomDto dto) throws ValidationException {
        Room room = new Room();
        room.setNumber(dto.getNumber());
        try {
            room.setCorpus(corpusService.readById(dto.getCorpusId()));
        } catch (NotFoundException e) {
            throw new ValidationException(e.getMessage());
        }
        if(roomRepository.findByRoomAndCorpus(dto.getNumber(), dto.getCorpusId()).isPresent()) {
            throw new ValidationException(String.format("Room(number=%d; corpusId=%dP already exists", dto.getNumber(), dto.getCorpusId()));
        }

        return roomRepository.save(room);
    }

    public Room updateRoom(long roomId, UpdateRoomDto dto) throws NotFoundException, ValidationException {
        Room room = readById(roomId);
        if(dto.getNumber() != null) {
            room.setNumber(room.getNumber());
        }
        if(dto.getCorpusId() != null) {
            try {
                room.setCorpus(corpusService.readById(dto.getCorpusId()));
            } catch (NotFoundException e) {
                throw new ValidationException(e.getMessage());
            }
        }
        if(roomRepository.findByRoomAndCorpus(dto.getNumber(), dto.getCorpusId()).isPresent()) {
            throw new ValidationException(String.format("Room(number=%d; corpusId=%d already exists", dto.getNumber(), dto.getCorpusId()));
        }
        return roomRepository.save(room);
    }

    public void deleteRoom(long roomId) {
        if(!roomRepository.findById(roomId).isPresent()) {
            return;
        }
        roomRepository.deleteById(roomId);
    }
}
