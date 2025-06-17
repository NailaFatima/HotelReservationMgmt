package com.example.reservationmanagement.repositories;

import com.example.reservationmanagement.models.Room;
import com.example.reservationmanagement.models.RoomType;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    Room add(Room room);
    List<Room> getRooms();
    List<Room> getRoomsByRoomType(RoomType roomType);
    Room save(Room room);

    Optional<Room> findById(long roomId);
}
