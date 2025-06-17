package com.example.reservationmanagement.dtos;

import com.example.reservationmanagement.models.Booking;

public class MakeBookingResponseDto {
    private Booking booking;
    private ResponseStatus responseStatus;

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}
