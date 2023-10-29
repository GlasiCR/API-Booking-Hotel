package com.project.bookingHotel.controllers;

import com.project.bookingHotel.dtos.CancelBookingDto;
import com.project.bookingHotel.model.Hotel;
import com.project.bookingHotel.services.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    BookingService bookingService;

    @PutMapping("/{numberBooking}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity cancelBooking(@PathVariable(value = "numberBooking") UUID numberBooking, @RequestBody @Valid CancelBookingDto cancelBooking) {
        return bookingService.cancelBooking(numberBooking, cancelBooking);
    }
}
