package com.project.bookingHotel.dtos;

import com.project.bookingHotel.model.Hotel;
import com.project.bookingHotel.model.Room;
import com.project.bookingHotel.model.User;

import java.time.LocalDateTime;

public record RegisterBookingDto(LocalDateTime checkin, LocalDateTime checkout) {
}
