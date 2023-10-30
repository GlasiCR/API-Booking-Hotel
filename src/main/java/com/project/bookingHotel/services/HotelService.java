package com.project.bookingHotel.services;

import com.project.bookingHotel.dtos.RoomCreateDto;
import com.project.bookingHotel.model.Hotel;
import com.project.bookingHotel.model.Room;
import com.project.bookingHotel.repositories.HotelRepository;
import com.project.bookingHotel.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public Hotel create(Hotel hotel){
        return hotelRepository.save(hotel);
    }

    public List<Hotel> getAllHotels(){
        return hotelRepository.findAll();
    }

    /*public List<Hotel> getHotelByFilter(String query){
        return hotelRepository.findAll(query);
    }*/

    public ResponseEntity<List<Hotel>> findByCity(String city) {
        return hotelRepository.findByCity(city)
                .map(hotel -> ResponseEntity.ok().body(hotel))
                .orElse(ResponseEntity.notFound().build());
    }

    public List<Hotel> findByNameStartingWith(String name){
        return hotelRepository.findByNameStartingWith(name);
    }

    public ResponseEntity<?> findDetailsAboutHotelById(Long id) {
        Optional<Hotel> hotelAlready = hotelRepository.findById(id);

        if (hotelAlready.isPresent()) {
            Hotel hotel = hotelAlready.get();
            List<Room> roomsOfHotel = hotel.getRooms();

            List<Map<String, Object>> roomsInfo = new ArrayList<>();
            for (Room room : roomsOfHotel) {
                Map<String, Object> roomDetails = new HashMap<>();
                roomDetails.put("nameRoom", room.getNameRoom());
                roomDetails.put("price", room.getPrice());
                roomsInfo.add(roomDetails);
            }

            Map<String, Object> detailsHotel = new HashMap<>();
            detailsHotel.put("name", hotel.getName());
            detailsHotel.put("description", hotel.getDescriptionHotel());
            detailsHotel.put("rooms", roomsInfo);

            return ResponseEntity.ok().body(detailsHotel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
