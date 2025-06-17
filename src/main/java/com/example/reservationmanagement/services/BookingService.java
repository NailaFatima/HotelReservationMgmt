package com.example.reservationmanagement.services;

import com.example.reservationmanagement.exceptions.CustomerSessionNotFoundException;
import com.example.reservationmanagement.exceptions.InvalidRoomException;
import com.example.reservationmanagement.exceptions.UserNotFoundException;
import com.example.reservationmanagement.models.Booking;
import com.example.reservationmanagement.models.Invoice;

import java.util.Map;

public interface BookingService {
    Booking makeBooking(long userId, Map<Long, Integer> roomsToBeBooked) throws UserNotFoundException, InvalidRoomException;
    Invoice generateInvoice(long userId) throws CustomerSessionNotFoundException;

}
