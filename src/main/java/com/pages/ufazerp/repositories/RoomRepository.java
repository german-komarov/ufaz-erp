package com.pages.ufazerp.repositories;

import com.pages.ufazerp.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("select r from Room r where r.number=:roomNumber and r.corpus.id=:corpusId")
    Optional<Room> findByRoomAndCorpus(int roomNumber, long corpusId);
}
