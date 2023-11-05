package com.project.bookingHotel.controllers;

import com.project.bookingHotel.dtos.RoomCreateDto;
import com.project.bookingHotel.model.Calendar;
import com.project.bookingHotel.model.Room;
import com.project.bookingHotel.services.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping("/")
    public ResponseEntity<Room> createRoom(@RequestBody @Valid RoomCreateDto room) {
        return roomService.createRoom(room);
    }

    @GetMapping("/{hotelId}")
    public List<Room> getRoomsByHotelId(@PathVariable Long hotelId) {
        return roomService.getRoomsByHotelId(hotelId);
    }

    @GetMapping("/filterDate/{idRoom}")
    @ResponseStatus(HttpStatus.OK)
    public List<Calendar> getRoomOccupancy(@PathVariable(value = "idRoom") Long room){
        return roomService.getRoomOccupancy(room);
    }
}
