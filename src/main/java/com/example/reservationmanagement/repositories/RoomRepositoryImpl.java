package com.example.reservationmanagement.repositories;

import com.example.reservationmanagement.models.Room;
import com.example.reservationmanagement.models.RoomType;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Repository
public class RoomRepositoryImpl implements RoomRepository {
    private Map<Long, Room> roomsMap;
    private int idCounter=0;

    public RoomRepositoryImpl() {
        roomsMap = new HashMap<>();
    }
    @Override
    public Room add(Room room) {
        if(room.getId() == 0){
            room.setId(++idCounter);
        }
        roomsMap.put(room.getId(), room);
        return room;
    }

    @Override
    public List<Room> getRooms() {
        return roomsMap.values().stream().toList();
    }

    @Override
    public List<Room> getRoomsByRoomType(RoomType roomType) {
        return roomsMap.values().stream().filter(r -> r.getRoomType().equals(roomType)).toList();
    }

    @Override
    public Room save(Room room) {
        if(room.getId() == 0){

            room.setId(idCounter++);
        }
        roomsMap.put(room.getId(), room);
        return room;
    }

    @Override
    public Optional<Room> findById(long roomId) {
        Optional<Room> first= roomsMap.values().stream().filter(room -> room.getId() == roomId).findFirst();
        return first;
    }
}
