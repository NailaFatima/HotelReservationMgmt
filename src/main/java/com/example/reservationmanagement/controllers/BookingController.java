package com.example.reservationmanagement.controllers;

import com.example.reservationmanagement.dtos.*;
import com.example.reservationmanagement.models.Booking;
import com.example.reservationmanagement.services.BookingService;
import com.example.reservationmanagement.dtos.MakeBookingRequestDto;
import com.example.reservationmanagement.dtos.MakeBookingResponseDto;
import com.example.reservationmanagement.models.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public GenerateInvoiceResponseDto generateInvoice(GenerateInvoiceRequestDto requestDto) {
        GenerateInvoiceResponseDto responseDto = new GenerateInvoiceResponseDto();

        try{
            Invoice invoice = bookingService.generateInvoice(requestDto.getUserId());
            responseDto.setInvoice(invoice);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);

        }

        catch(Exception e){
            responseDto.setInvoice(null);
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }

    public MakeBookingResponseDto makeBooking(MakeBookingRequestDto makeBookingRequestDto) {
        MakeBookingResponseDto responseDto = new MakeBookingResponseDto();
        try{
            Booking booking = bookingService.makeBooking(makeBookingRequestDto.getUserId(), makeBookingRequestDto.getBookedRooms());
            responseDto.setBooking(booking);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            return responseDto;
        }
        catch (Exception ignored){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            return responseDto;
        }
    }
}
