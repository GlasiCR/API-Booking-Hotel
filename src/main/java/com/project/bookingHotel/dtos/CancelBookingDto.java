package com.project.bookingHotel.dtos;

import java.time.LocalDate;

public record CancelBookingDto(Long user, LocalDate todayDate) {
}
