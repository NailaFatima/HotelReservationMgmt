package com.example.reservationmanagement.services;

import com.example.reservationmanagement.models.Room;
import com.example.reservationmanagement.models.RoomType;
import com.example.reservationmanagement.models.User;
import com.example.reservationmanagement.repositories.RoomRepository;
import com.example.reservationmanagement.exceptions.UnAuthorizedAccess;
import com.example.reservationmanagement.exceptions.UserNotFoundException;
import com.example.reservationmanagement.models.UserType;
import com.example.reservationmanagement.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RoomServiceImpl implements RoomService{
    private RoomRepository roomRepository;
    private UserRepository userRepository;

    public RoomServiceImpl(RoomRepository roomRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Room> getRooms(String roomType) {
        if(roomType == null){
            return roomRepository.getRooms();
        }
        else{
            return roomRepository.getRoomsByRoomType(RoomType.valueOf(roomType));
        }

    }
    @Override
    public Room addRoom(long userId, String roomName, double price, String roomType,
                        String description) throws UserNotFoundException, UnAuthorizedAccess {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) {
            throw new UserNotFoundException("User Not Found");
        }
        User user = userOptional.get();
        if(user.getUserType()!= UserType.ADMIN){
            throw new UnAuthorizedAccess("Only administrators can add rooms");
        }
        Room room = new Room();
        room.setName(roomName);
        room.setPrice(price);
        room.setRoomType(RoomType.valueOf(roomType));
        room.setDescription(description);

        return roomRepository.save(room);
    }
}
