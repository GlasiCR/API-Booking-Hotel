package com.project.bookingHotel.dtos;

public record RoomCreateDto(String nameRoom, Integer price, Integer capacity, Integer numberOfRooms, Integer numberOfVacantRooms, Long idHotel) {
}