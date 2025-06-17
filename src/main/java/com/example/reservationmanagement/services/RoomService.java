package com.example.reservationmanagement.services;

import com.example.reservationmanagement.models.Room;
import com.example.reservationmanagement.exceptions.UnAuthorizedAccess;
import com.example.reservationmanagement.exceptions.UserNotFoundException;

import java.util.List;

public interface RoomService {
    Room addRoom(long userId, String roomName, double price, String roomType, String description) throws UserNotFoundException, UnAuthorizedAccess;
    List<Room> getRooms(String roomType);
}
