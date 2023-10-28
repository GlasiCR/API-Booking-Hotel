package com.project.bookingHotel.services;

import com.project.bookingHotel.dtos.RegisterBookingDto;
import com.project.bookingHotel.enums.StatusBooking;
import com.project.bookingHotel.model.Booking;
import com.project.bookingHotel.model.Hotel;
import com.project.bookingHotel.model.Room;
import com.project.bookingHotel.model.User;
import com.project.bookingHotel.repositories.BookingRepository;
import com.project.bookingHotel.repositories.HotelRepository;
import com.project.bookingHotel.repositories.RoomRepository;
import com.project.bookingHotel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.Optional;
import java.util.UUID;

import static com.project.bookingHotel.enums.StatusBooking.PAGO;

@Service
public class BookingService {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    BookingRepository bookingRepository;
    public UUID registerBooking(RegisterBookingDto booking, Long hotel, Long room){
        Optional<User> userAlready = userRepository.findById(booking.user());
        System.out.println(userAlready);
        if(!userAlready.isPresent()){
            throw new RuntimeException("Error 404 - Usuário não encontrado");
        }

        Optional<Hotel> hotelAlready = hotelRepository.findById(hotel);
        if(hotelAlready == null){
            throw new RuntimeException("Error 404 - Hotel não encontrado");
        }

        Optional<Room> roomAlready = roomRepository.findById(room);
        if(!roomAlready.isPresent()){
            throw new RuntimeException("Error 404 - Quarto não existe");
        }
        // 1 Período da reserva
        Period periodBooking = Period.between(booking.checkin(), booking.checkout());
        Integer daysBooking = periodBooking.getDays();

        // 2 valor total da reserva
        Room roomBooking = roomAlready.get();
        Integer priceBooking = daysBooking * roomBooking.getPrice();
        StatusBooking statusBooking = PAGO;

        // 3 Registrar reserva E Obter código de confirmação da reserva
        User userBooking = userAlready.get();
        Booking newBooking = new Booking(booking.checkin(), booking.checkout(), userBooking, priceBooking, daysBooking, booking.numberCreditCard(), statusBooking);
        bookingRepository.saveAndFlush(newBooking);

        // 4 Incluir reserva no usuário

        userBooking.addBooking(newBooking);
        userRepository.save(userBooking);

        // 5 Comunicar hotel sobre reserva
        Hotel hotelBooking = hotelAlready.get();
        hotelBooking.addBookingInHotel(newBooking);
        hotelRepository.save(hotelBooking);

        // 6 Comunicar quarto da reserva
        roomBooking.addBookingInRoom(newBooking);
        roomRepository.save(roomBooking);

        return newBooking.getId();
    }
}
