package com.project.bookingHotel.services;

import com.project.bookingHotel.dtos.RoomCreateDto;
import com.project.bookingHotel.model.Calendar;
import com.project.bookingHotel.model.Hotel;
import com.project.bookingHotel.model.Room;
import com.project.bookingHotel.repositories.CalendarRepository;
import com.project.bookingHotel.repositories.HotelRepository;
import com.project.bookingHotel.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private CalendarRepository calendarRepository;

    public ResponseEntity<Room> createRoom(RoomCreateDto room){
        Optional<Hotel> hotelAlready = hotelRepository.findById(room.hotel());
        if(hotelAlready.isPresent()){
            Hotel hotel = hotelAlready.get();
            Room newRoom = new Room();
            newRoom.setNameRoom(room.nameRoom());
            newRoom.setPrice(room.price());
            newRoom.setCapacity(room.capacity());
            newRoom.setHotel(hotel);
            roomRepository.saveAndFlush(newRoom);

            hotel.getRooms().add(newRoom);
            hotelRepository.save(hotel);

            return ResponseEntity.ok().body(newRoom);
        }
        return ResponseEntity.notFound().build();
    }

    public List<Room> getRoomsByHotelId(Long hotelId) {
        return roomRepository.findByHotelId(hotelId);
    }

   public void getRoomOccupancy(Long room){
        Optional<Room> roomAlready = roomRepository.findById(room);
        Room roomRequest = roomAlready.get();
        List<Calendar> datesRoomOccupancy = roomRepository.findByCalendar(roomRequest);
        System.out.println(datesRoomOccupancy);
    }
}