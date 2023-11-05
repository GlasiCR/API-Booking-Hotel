package com.project.bookingHotel.services;

import com.project.bookingHotel.dtos.ResearchAvailableCalendarDto;
import com.project.bookingHotel.model.Calendar;
import com.project.bookingHotel.model.Hotel;
import com.project.bookingHotel.model.Room;
import com.project.bookingHotel.repositories.CalendarRepository;
import com.project.bookingHotel.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CalendarService {
    @Autowired
    CalendarRepository calendarRepository;
    @Autowired
    HotelRepository hotelRepository;

    public List<Room> filterByAvailableCalendar(LocalDate checkin, LocalDate checkout, ResearchAvailableCalendarDto researchAvailableCalendar) {
        Optional<Hotel> hotelAlready = hotelRepository.findById(researchAvailableCalendar.idHotel());
        //if (!hotelAlready.isPresent()) {
          //  System.out.println("Hotel not exists");
        //}
        if (hotelAlready.isPresent()) {
            Hotel hotel = hotelAlready.get();
            List<Room> roomsAvailable = new ArrayList<>();
            for (Room room : hotel.getRooms()) {
                boolean dateAvailable = true;

                for (Calendar calendar : room.getCalendar()) {
                    List<LocalDate> datesOcupaccy = calendar.getDatesOcupaccy();
                    if (datesOcupaccy != null && datesOcupaccy.contains(checkout) ) {
                    //if (!calendar.getDatesOcupaccy().contains(calendar)) {
                        dateAvailable = false;
                    }
                }
                if (dateAvailable) {
                    roomsAvailable.add(room);
                }
            }
            return roomsAvailable;
        }else {
            throw new RuntimeException("Não há disponibilidade para a data desejada");
        }
    }
}
