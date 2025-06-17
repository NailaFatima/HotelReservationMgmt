package com.example.reservationmanagement.repositories;

import com.example.reservationmanagement.models.Booking;

import java.util.List;

public interface BookingRepository {
    Booking save(Booking booking);
    List<Booking> findBookingByCustomerSession(long customerSessionId);
}
