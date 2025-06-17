package com.example.reservationmanagement.repositories;

import com.example.reservationmanagement.models.Booking;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class BookingRepositoryImpl implements BookingRepository{
    private Map<Long,Booking> bookings;
    private int idCounter=0;

    public BookingRepositoryImpl() {
        this.bookings = new HashMap<>();
    }

    @Override
    public Booking save(Booking booking) {
        if(booking.getId() == 0){
            booking.setId(++idCounter);
        }
        bookings.put(booking.getId(), booking);

        return booking;
    }
    @Override
    public List<Booking> findBookingByCustomerSession(long customerSessionId) {
        return bookings.values().stream().filter(bookSession-> bookSession.getCustomerSession().getId() == customerSessionId).toList();

    }
}
