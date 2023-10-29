package com.project.bookingHotel.services;

import com.project.bookingHotel.dtos.CancelBookingDto;
import com.project.bookingHotel.dtos.RegisterBookingDto;
import com.project.bookingHotel.enums.StatusBooking;
import com.project.bookingHotel.model.*;
import com.project.bookingHotel.repositories.*;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.project.bookingHotel.enums.StatusBooking.CANCELADO;
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

    public Booking registerBooking(RegisterBookingDto booking, Long hotel, Long room){
       /* List<Calendar> calendarAlready = calendarRepository.findByRooms(room);

        if (!calendarAlready.isEmpty()) {
            // Verifica se as datas entre check-in e check-out se sobrepõem com os calendários existentes
            for (Calendar calendar : calendarAlready) {
                if(booking.checkin().isBefore(calendar.getDateCheckout()) && calendar.getDateCheckin().isBefore(booking.checkout())){
                    throw new RuntimeException("Error 404 - Não há disponibilidade para o período");
                }
            }
        }*/

        Optional<User> userAlready = userRepository.findById(booking.user());
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

        Period periodBooking = Period.between(booking.checkin(), booking.checkout());
        Integer daysBooking = periodBooking.getDays();

        Room roomBooking = roomAlready.get();
        Integer priceBooking = daysBooking * roomBooking.getPrice();
        StatusBooking statusBooking = PAGO;

        User userBooking = userAlready.get();
        Booking newBooking = new Booking(booking.checkin(), booking.checkout(), userBooking, priceBooking, daysBooking, booking.numberCreditCard(), statusBooking, booking.quantityGuest());
        bookingRepository.saveAndFlush(newBooking);

        userBooking.addBooking(newBooking);
        userRepository.save(userBooking);

        Hotel hotelBooking = hotelAlready.get();
        hotelBooking.addBookingInHotel(newBooking);
        hotelRepository.save(hotelBooking);

        roomBooking.addBookingInRoom(newBooking);
        roomRepository.save(roomBooking);

        return newBooking;
    }

    public ResponseEntity cancelBooking(UUID numberBooking, CancelBookingDto cancelBooking){
        Optional <User> userAlready = userRepository.findById(cancelBooking.user());
        if(!userAlready.isPresent()){
            return ResponseEntity.badRequest().build();
        }
        Optional<Booking> bookingAlready = bookingRepository.findById(numberBooking);
        if(!bookingAlready.isPresent()){
            return ResponseEntity.badRequest().build();
        }
        User user = userAlready.get();
        Booking booking = bookingAlready.get();
        if(booking.getUser() == user){
            Period periodBetweenCheckinAndCurrentDate = Period.between(cancelBooking.todayDate(), booking.getCheckin());
            Integer differenceInDays =  periodBetweenCheckinAndCurrentDate.getDays();
                    if(differenceInDays <= 7){
                        return ResponseEntity.badRequest().body("Não foi possível cancelar a resrva, pois não está no prazo permitido para solicitação");
                    }
        }
        booking.setStatusBooking(StatusBooking.CANCELADO);
        bookingRepository.save(booking);
        return ResponseEntity.ok().body("Cancelamento realizado com sucesso");
    }
}
