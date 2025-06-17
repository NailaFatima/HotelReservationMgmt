package com.example.reservationmanagement.controllers;

import com.example.reservationmanagement.dtos.*;
import com.example.reservationmanagement.models.Room;
import com.example.reservationmanagement.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public class RoomController {
    private RoomService roomService;
    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }
    public AddRoomResponseDto addRoom(AddRoomRequestDto requestDto) {
        AddRoomResponseDto responseDto = new AddRoomResponseDto();
        try {
            Room room = roomService.addRoom(requestDto.getUserId(), requestDto.getName(), requestDto.getPrice(),
                    requestDto.getRoomType(), requestDto.getDescription());
            responseDto.setRoom(room);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            return responseDto;
        }
        catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            return responseDto;

        }

    }

    public GetRoomsResponseDto getRooms(GetRoomsRequestDto requestDto) {
        GetRoomsResponseDto responseDto = new GetRoomsResponseDto();
        try{
           List<Room> room = roomService.getRooms(requestDto.getRoomType());
           responseDto.setRooms(room);
           responseDto.setResponseStatus(ResponseStatus.SUCCESS);
           return responseDto;
        }
        catch(Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            return responseDto;
        }

    }
}
