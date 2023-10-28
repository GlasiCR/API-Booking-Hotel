package com.project.bookingHotel.dtos;

import java.time.LocalDate;

public record RegisterBookingDto(LocalDate checkin, LocalDate checkout, Long user, Long numberCreditCard, Integer quantityGuest) {
}
