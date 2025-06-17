package com.example.reservationmanagement.services;

import com.example.reservationmanagement.exceptions.CustomerSessionNotFoundException;
import com.example.reservationmanagement.exceptions.InvalidRoomException;
import com.example.reservationmanagement.exceptions.UserNotFoundException;
import com.example.reservationmanagement.models.*;
import com.example.reservationmanagement.repositories.BookingRepository;
import com.example.reservationmanagement.repositories.CustomerSessionRepository;
import com.example.reservationmanagement.repositories.RoomRepository;
import com.example.reservationmanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class BookingServiceImpl implements BookingService {
    private BookingRepository bookingRepo;
    private CustomerSessionRepository sessionRepo;
    private RoomRepository roomRepo;
    private UserRepository userRepo;

    public BookingServiceImpl(BookingRepository bookingRepo, CustomerSessionRepository sessionRepo, RoomRepository roomRepo, UserRepository userRepo) {
        this.bookingRepo = bookingRepo;
        this.sessionRepo = sessionRepo;
        this.roomRepo = roomRepo;
        this.userRepo = userRepo;
    }

//    public BookingServiceImpl(BookingRepository bookingRepo, CustomerSessionRepository sessionRepo) {
//        this.bookingRepo = bookingRepo;
//        this.sessionRepo = sessionRepo;
//    }

    @Override
    public Booking makeBooking(long userId, Map<Long, Integer> roomsToBeBooked) throws UserNotFoundException, InvalidRoomException {
        Optional<CustomerSession> session= sessionRepo.findActiveCustomerSessionByUserId(userId);
        CustomerSession customerSession;
        if(session.isEmpty()) {
            customerSession = new CustomerSession();
            Optional<User> user = userRepo.findById(userId);
            if(user.isEmpty()) {
                throw new UserNotFoundException("User not found");
            }
            customerSession.setUser(user.get());
            customerSession.setCustomerSessionStatus(CustomerSessionStatus.ACTIVE);
            customerSession = sessionRepo.save(customerSession);
        }
        else{
            customerSession = session.get();
        }
        Map<Room, Integer> bookedRooms = new HashMap<>();
        for(Map.Entry<Long, Integer> entry : roomsToBeBooked.entrySet()) {
            long roomId = entry.getKey();
            int quantity = entry.getValue();
            Optional<Room> optionalRoom = roomRepo.findById(roomId);
            Room room = optionalRoom.orElseThrow(() -> new InvalidRoomException("Room not found"));
            bookedRooms.put(room, quantity);
        }
        Booking booking = new Booking();
        booking.setCustomerSession(customerSession);
        booking.setBookedRooms(bookedRooms);
        return bookingRepo.save(booking);
    }
    @Override
    public Invoice generateInvoice(long userId) throws CustomerSessionNotFoundException {
        CustomerSession customerSession = sessionRepo.findActiveCustomerSessionByUserId(userId).orElse(null);

        if (customerSession== null) {
            throw new CustomerSessionNotFoundException("Customer session not found");
        }
        Invoice invoice = new Invoice();
        invoice.setId(userId);
        customerSession.setCustomerSessionStatus(CustomerSessionStatus.ENDED);
        sessionRepo.save(customerSession);

        double totalAmount = 0.0;
        double totalGst = 0.0;
        double totalService= 0.0;
        Map<Room, Integer> bookedRooms= new HashMap<>();
        List<Booking> bookings = bookingRepo.findBookingByCustomerSession(customerSession.getId());
        for (Booking booking : bookings) {
            for(Map.Entry<Room, Integer> m : booking.getBookedRooms().entrySet()){
                totalAmount += m.getValue() * m.getKey().getPrice();
                if(bookedRooms.containsKey(m.getKey())){
                    bookedRooms.put(m.getKey(), bookedRooms.get(m.getKey()) + m.getValue());
                }
                else{
                    bookedRooms.put(m.getKey(), m.getValue());
                }
            }
        }
        totalGst= 0.18 * totalAmount;
        totalService= 0.1 * totalAmount;
        double finalAmount= totalAmount + totalGst + totalService;
        invoice.setTotalAmount(finalAmount);
        invoice.setGst(totalGst);
        invoice.setServiceCharge(totalService);
        invoice.setBookedRooms(bookedRooms);

        return invoice;
    }
}
