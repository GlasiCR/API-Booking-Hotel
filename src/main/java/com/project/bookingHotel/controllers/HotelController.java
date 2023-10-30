package com.project.bookingHotel.controllers;

import com.project.bookingHotel.dtos.RegisterBookingDto;
import com.project.bookingHotel.model.Booking;
import com.project.bookingHotel.model.Hotel;
import com.project.bookingHotel.services.BookingService;
import com.project.bookingHotel.services.HotelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hotels")
public class HotelController {
  @Autowired
  private HotelService hotelService;
  @Autowired
  private BookingService bookingService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Hotel create(@RequestBody Hotel hotel) {
        return hotelService.create(hotel);
    }

    @GetMapping("/location")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Hotel>> findByCity(@RequestParam String city){
      return hotelService.findByCity(city);
    }

    @GetMapping("/name")
    @ResponseStatus(HttpStatus.OK)
    public List<Hotel> findByNameStartingWith(@RequestParam String name) {
      return hotelService.findByNameStartingWith(name);
    }

    @GetMapping("/{id}/details")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findDescriptionHotelById(@PathVariable(value = "id") Long id){
      return hotelService.findDetailsAboutHotelById(id);
    }

    @PostMapping("/{idHotel}/booking/{idRoom}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Booking> registerBooking(@PathVariable(value = "idRoom") Long room, @PathVariable(value = "idHotel") Long hotel, @RequestBody @Valid RegisterBookingDto booking){
      return bookingService.registerBooking(booking, hotel, room) ;
    }
}
