package com.project.bookingHotel.dtos;

import java.time.LocalDateTime;

public record NewBookingDto(LocalDateTime checkin, LocalDateTime checkout, Long hotel, Long room) {
}
