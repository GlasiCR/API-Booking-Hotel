package com.project.bookingHotel.services;

import com.project.bookingHotel.dtos.RegisterBookingDto;
import com.project.bookingHotel.model.Booking;
import com.project.bookingHotel.model.Hotel;
import com.project.bookingHotel.repositories.HotelRepository;
import com.project.bookingHotel.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    HotelRepository hotelRepository;
    public void registerBooking(Booking booking){
        var idHotel = booking.getHotel();
        Hotel hotelBooking = hotelRepository.findById(booking.getHotel());
        System.out.println(hotelBooking);
    }
}
