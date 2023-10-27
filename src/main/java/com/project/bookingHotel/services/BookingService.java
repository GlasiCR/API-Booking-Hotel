package com.project.bookingHotel.services;

import com.project.bookingHotel.dtos.RegisterBookingDto;
import com.project.bookingHotel.model.Booking;
import com.project.bookingHotel.model.Hotel;
import com.project.bookingHotel.model.Room;
import com.project.bookingHotel.model.User;
import com.project.bookingHotel.repositories.HotelRepository;
import com.project.bookingHotel.repositories.RoomRepository;
import com.project.bookingHotel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    UserRepository userRepository;
    public void registerBooking(RegisterBookingDto booking, Hotel hotel, Room room){
        //var idHotel = booking.getHotel();
        //Hotel hotelBooking = hotelRepository.findById(booking.getHotel());
        //var idUser = booking.getUser();
        Optional<User> userBooking = userRepository.findById(booking.user());
        //Optional<Hotel> hotelBooking = hotelRepository.findById(hotel.getId());

        System.out.println(userBooking);
        System.out.println(hotel);
        System.out.println(room);
    }
}
