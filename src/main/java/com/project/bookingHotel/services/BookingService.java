package com.project.bookingHotel.services;

import com.project.bookingHotel.dtos.CancelBookingDto;
import com.project.bookingHotel.dtos.RegisterBookingDto;
import com.project.bookingHotel.enums.StatusBooking;
import com.project.bookingHotel.model.*;
import com.project.bookingHotel.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


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
    @Autowired
    CalendarRepository calendarRepository;

    public ResponseEntity<Booking> registerBooking(RegisterBookingDto booking, Long hotel, Long room){
        //Quarto existe?
        Optional<Room> roomAlready = roomRepository.findById(room);
        if(!roomAlready.isPresent()){
            throw new RuntimeException("Error 404 - Quarto não existe");
        }
        Room roomBooking = roomAlready.get();

        //Existe disponibilidade no quarto para a data desejada
        List<Calendar> listCalendarByRoom = calendarRepository.findByRoom(roomBooking);
        if (!listCalendarByRoom.isEmpty()) {
            //Há sobreposição de data?
            for (Calendar calendar : listCalendarByRoom) {
                if(booking.checkin().isBefore(calendar.getDateCheckout()) && calendar.getDateCheckin().isBefore(booking.checkout())){
                    throw new RuntimeException("Error 404 - Não há disponibilidade para o período");
                }
            }
        }

        //Usuário existe
        Optional<User> userAlready = userRepository.findById(booking.user());
        if(!userAlready.isPresent()){
            throw new RuntimeException("Error 404 - Usuário não encontrado");
        }

        //Hotel existe
        Optional<Hotel> hotelAlready = hotelRepository.findById(hotel);
        if(hotelAlready == null){
            throw new RuntimeException("Error 404 - Hotel não encontrado");
        }

        //Quantos dias a hospedagem
        Period periodBooking = Period.between(booking.checkin(), booking.checkout());
        Integer daysBooking = periodBooking.getDays();

        //Valor total da estadia
        Integer priceBooking = daysBooking * roomBooking.getPrice();
        StatusBooking statusBooking = PAGO;

        //Tudo OK, crio a reserva no banco de dados
        User userBooking = userAlready.get();
        Booking newBooking = new Booking(booking.checkin(), booking.checkout(), userBooking, priceBooking, daysBooking, booking.numberCreditCard(), statusBooking, booking.quantityGuest());
        bookingRepository.saveAndFlush(newBooking);

        //Inclui reserva para o usuário
        userBooking.addBooking(newBooking);
        userRepository.save(userBooking);

        //Inclui reserva para o hotel
        Hotel hotelBooking = hotelAlready.get();
        hotelBooking.addBookingInHotel(newBooking);
        hotelRepository.save(hotelBooking);

        //Inclui reserva para o quarto
        roomBooking.addBookingInRoom(newBooking);
        roomRepository.save(roomBooking);

        //Registro ocupação de datas em calendário
        Calendar newCalendar = new Calendar(booking.checkin(), booking.checkout(), daysBooking, newBooking.getId(), roomBooking);
        calendarRepository.save(newCalendar);

        return ResponseEntity.ok().body(newBooking);
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
